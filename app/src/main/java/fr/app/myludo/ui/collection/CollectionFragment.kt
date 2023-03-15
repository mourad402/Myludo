package fr.app.myludo.ui.collection

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.app.myludo.databinding.FragmentCollectionBinding

class CollectionFragment : Fragment() {

    private var _binding: FragmentCollectionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val collectionViewModel =
            ViewModelProvider(this)[CollectionViewModel::class.java]

        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView = binding.listView
        val emptyView = binding.emptyView
        val adapter = ArrayAdapter<Any?>(
            requireContext(), R.layout.simple_list_item_1,
                arrayOf(1, "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a", 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 91, 2, 3, 4, 5, 6, 7, 8, 91, 2, 3, 4, 5, 6, 7, 8, 9)
        )
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, i, _ ->
            Toast.makeText(
                requireContext(), adapterView.getItemAtPosition(i).toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
        listView.emptyView = emptyView

        val textView: TextView = binding.textCollection
        collectionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}