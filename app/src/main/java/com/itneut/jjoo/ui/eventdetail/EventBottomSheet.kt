import android.icu.text.SimpleDateFormat
import com.itneut.jjoo.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.ParseException
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itneut.jjoo.data.Event
import com.itneut.jjoo.data.Purchase
import com.itneut.jjoo.repositories.PurchaseRepository
import com.itneut.jjoo.repositories.UserRepository
import com.itneut.jjoo.utils.Format
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale
import kotlin.text.format

class EventBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(event: Event): EventBottomSheet {
            val fragment = EventBottomSheet()
            val bundle = Bundle().apply {
                putLong("id", event.id)
                putString("sportName", event.sport.name)
                putString("date", event.date)
                putString("place", event.place)
                putDouble("price", event.price)
                putString("time", event.hour)
                putString("sportLogo", event.sport.logo)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el diseño del Bottom Sheet
        val view = inflater.inflate(R.layout.fragment_event_bottom_sheet, container, false)

        // Extraer datos directamente desde el Bundle
        setupEventDetails(view)
        setupPurchaseFunctionality(view)

        return view
    }

    private fun setupEventDetails(view: View) {
        val sportName = arguments?.getString("sportName").orEmpty()
        val date = arguments?.getString("date").orEmpty()
        val place = arguments?.getString("place").orEmpty()
        val price = arguments?.getDouble("price") ?: 0.0
        val time = arguments?.getString("time").orEmpty()

        view.findViewById<TextView>(R.id.eventTitle).text = "Deporte: $sportName"
        view.findViewById<TextView>(R.id.eventDate).text =
            Format.date(date).toString().capitalize(Locale.ROOT)
        view.findViewById<TextView>(R.id.eventPlace).text = "Lugar: $place"
        view.findViewById<TextView>(R.id.eventPrice).text =
            "Precio: ${NumberFormat.getCurrencyInstance().format(price)}"
        view.findViewById<TextView>(R.id.eventTime).text = "Empieza a las $time"
    }

    private fun setupPurchaseFunctionality(view: View) {
        val buyButton = view.findViewById<Button>(R.id.btnBuyTicket)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupIntermediary)

        val eventId = arguments?.getLong("id")
        val baseAmount = arguments?.getDouble("price") ?: 0.0

        buyButton.isEnabled = false // Inicialmente desactivado
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            buyButton.isEnabled = checkedId != -1 // Activar si hay selección
        }

        buyButton.setOnClickListener {
            handlePurchase(radioGroup, baseAmount, eventId)
        }
    }

    private fun handlePurchase(radioGroup: RadioGroup, baseAmount: Double, eventId: Long?) {
        val selectedIntermediary = when (radioGroup.checkedRadioButtonId) {
            R.id.radioUltimateEvent -> "UltimateEvent"
            R.id.radioElite -> "Elite"
            R.id.radioTicketPro -> "TicketPro"
            else -> "Ninguno"
        }

        val loggedInUser = UserRepository.loggedInUser

        if (loggedInUser == null) {
            Toast.makeText(
                requireContext(),
                "Debes estar logueado para realizar una compra.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val finalAmount = calculateFinalAmount(baseAmount, selectedIntermediary)

        if (loggedInUser.money < finalAmount) {
            Toast.makeText(
                requireContext(),
                "No tienes suficiente dinero para realizar esta compra.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        loggedInUser.money -= finalAmount
        PurchaseRepository.add(
            Purchase(
                id = PurchaseRepository.get().size + 1L,
                userId = loggedInUser.id,
                eventId = eventId!!,
                amount = finalAmount,
                createdDate = LocalDate.now().toString(),
                seat = PurchaseRepository.getSeat()
            )
        )

        Toast.makeText(
            requireContext(),
            "Compra realizada con $selectedIntermediary! Monto total: ${
                Format.amount(finalAmount)
            }",
            Toast.LENGTH_LONG
        ).show()

        dismiss() // Cierra el Bottom Sheet
    }

    private fun calculateFinalAmount(baseAmount: Double, intermediary: String): Double {
        val now = LocalDateTime.now()
        return when (intermediary) {
            "TicketPro" -> baseAmount * 1.02 // Aplica una comisión del 2%
            "Elite" -> {
                // Si la hora está entre las 20:00 y las 23:59 aplica 1%, sino 3%
                val isEvening = now.hour in 20..23
                if (isEvening) baseAmount * 1.01 else baseAmount * 1.03
            }

            "UltimateEvent" -> {
                // Si es sábado o domingo aplica 3%, sino 0.75%
                val isWeekend =
                    now.dayOfWeek == DayOfWeek.SATURDAY || now.dayOfWeek == DayOfWeek.SUNDAY
                if (isWeekend) baseAmount * 1.03 else baseAmount * 1.0075
            }

            else -> baseAmount // Si no hay intermediario válido, no se aplica comisión
        }
    }
}
