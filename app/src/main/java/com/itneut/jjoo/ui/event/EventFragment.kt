package com.itneut.jjoo.ui.event

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.R
import com.itneut.jjoo.repositories.EventRepository

class EventFragment : Fragment(R.layout.fragment_event) {

    private lateinit var eventAdapter: EventAdapter
    private lateinit var tvNoResults: TextView // TextView para el mensaje de "No se encontraron eventos"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuración del RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvEvents)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializa el adaptador con todos los eventos
        eventAdapter = EventAdapter(EventRepository.get()) { selectedEvent ->
            val bottomSheet = EventBottomSheet.newInstance(selectedEvent)
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
        recyclerView.adapter = eventAdapter

        // Configuración del TextView para "No se encontraron eventos"
        tvNoResults = view.findViewById(R.id.tvNoResults)

        // Configuración del SearchView
        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.queryHint = "Busque por lugar" // Establece un texto de pista

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterEvents(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterEvents(newText)
                return true
            }
        })
    }

    private fun filterEvents(query: String?) {
        val filteredEvents = if (query.isNullOrEmpty()) {
            EventRepository.get()
        } else {
            EventRepository.get().filter { event ->
                event.place.contains(query, ignoreCase = true)
            }
        }

        // Mostrar el mensaje si no se encuentran resultados
        if (filteredEvents.isEmpty()) {
            tvNoResults.visibility = View.VISIBLE
        } else {
            tvNoResults.visibility = View.GONE
        }

        // Actualizar la lista del RecyclerView
        eventAdapter.updateEvents(filteredEvents)
    }
}
