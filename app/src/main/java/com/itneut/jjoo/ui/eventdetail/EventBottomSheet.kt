import com.itneut.jjoo.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itneut.jjoo.data.Event
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.NumberFormat

class EventBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(event: Event): EventBottomSheet {
            val fragment = EventBottomSheet()
            val bundle = Bundle().apply {
                putString("title", event.sport.name)
                putString("date", event.date)
                putString("place", event.place)
                putDouble("price", event.price)
                putString("time", event.hour)
                putString("sport", event.sport.logo)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val priceFormatter = DecimalFormat("#,###.##")
        // Infla el diseño del Bottom Sheet
        val view = inflater.inflate(R.layout.fragment_event_bottom_sheet, container, false)

        // Extrae los datos del Bundle
        val title = arguments?.getString("title")
        val date = arguments?.getString("date")
        val place = arguments?.getString("place")
        val price = arguments?.getDouble("price")
        val time = arguments?.getString("time")
        val sportLogo = arguments?.getString("sport")
        Log.d("EventBottomSheet", "Sport logo: $sportLogo")

        // Asignando valores a los elementos de la vista
        view.findViewById<TextView>(R.id.eventTitle).text = title
        view.findViewById<TextView>(R.id.eventDate).text = date
        view.findViewById<TextView>(R.id.eventPlace).text = place
        view.findViewById<TextView>(R.id.eventPrice).text = priceFormatter.format(price)
        view.findViewById<TextView>(R.id.eventTime).text = "Empieza a las ${time}h.s"
        Picasso.get().load(sportLogo).resize(150,150).centerCrop()
            .into(view.findViewById<ImageView>(R.id.eventSportLogo))
        return view
    }
}


