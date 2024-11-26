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

        // Extrae los datos del Bundle
        val id = arguments?.getLong("id")
        val sportName = arguments?.getString("sportName")
        val date = arguments?.getString("date")
        val place = arguments?.getString("place")
        val price = arguments?.getDouble("price")
        val time = arguments?.getString("time")
        //val sportLogo = arguments?.getString("sportLogo")

        // Asignando valores a los elementos de la vista
        view.findViewById<TextView>(R.id.eventTitle).text = "Deporte: $sportName"
        view.findViewById<TextView>(R.id.eventDate).text =
            formatDate(date.toString()).toString().capitalize()
        view.findViewById<TextView>(R.id.eventPlace).text = "Lugar: $place"
        view.findViewById<TextView>(R.id.eventPrice).text =
            "Precio: ${NumberFormat.getCurrencyInstance().format(price)}"
        view.findViewById<TextView>(R.id.eventTime).text = "Empieza a las ${time}"

        // Configuración del botón
        val buyButton = view.findViewById<Button>(R.id.btnBuyTicket)
        buyButton.isEnabled = false // Inicialmente desactivado

        // Listener del RadioGroup
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupIntermediary)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) { // Si algún RadioButton está seleccionado
                buyButton.isEnabled = true // Activar el botón
            }
        }
        // Listener para el botón "Comprar Entrada"
        buyButton.setOnClickListener {
            val selectedIntermediary = when (radioGroup.checkedRadioButtonId) {
                R.id.radioUltimateEvent -> "UltimateEvent"
                R.id.radioElite -> "Elite"
                R.id.radioTicketPro -> "TicketPro"
                else -> "Ninguno"
            }

            val baseAmount = price ?: 0.0
            val finalAmount = calculateFinalAmount(baseAmount, selectedIntermediary)
            UserRepository.loggedInUser?.money = UserRepository.loggedInUser!!.money - finalAmount
            PurchaseRepository.add(Purchase(PurchaseRepository.get().size + 1L, UserRepository.loggedInUser!!.id, id!!, finalAmount,
                LocalDate.now().toString(), PurchaseRepository.getSeat()))
            Toast.makeText(requireContext(), "Compra realizada! $selectedIntermediary", Toast.LENGTH_SHORT).show()

            //Cierra el BottomSheet después de la compra
            dismiss()
        }
        //Picasso.get().load(sportLogo).resize(150, 150).centerCrop()            .into(view.findViewById<ImageView>(R.id.eventSportLogo))
        return view
    }

    fun calculateFinalAmount(baseAmount: Double = 0.0, intermediary: String): Double {
        val now = LocalDateTime.now() // Fecha y hora actual
        val dayOfWeek = now.dayOfWeek
        val hour = now.hour

        return when (intermediary) {
            "TicketPro" -> baseAmount * 1.02 // Comisión fija del 2%
            "Elite" -> {
                // Verifica si está en el rango horario de 20:00 a 23:59
                val isDiscountHour = hour in 20..23
                baseAmount * if (isDiscountHour) 1.01 else 1.03
            }
            "UltimateEvent" -> {
                // Verifica si es sábado o domingo
                val isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
                baseAmount * if (isWeekend) 1.03 else 1.0075
            }
            else -> baseAmount // Si no se selecciona un intermediario, no aplica comisión
        }
    }


    fun formatDate(
        inputDate: String,
        inputFormat: String = "yyyy-MM-dd",
        outputFormat: String = "EEEE, MMMM d",
        locale: Locale = Locale("es", "ES")
    ): String? {
        val inputDateFormat = SimpleDateFormat(inputFormat, locale)
        val date = inputDateFormat.parse(inputDate)

        val outputDateFormat = SimpleDateFormat(outputFormat, locale)
        return outputDateFormat.format(date)

    }

}