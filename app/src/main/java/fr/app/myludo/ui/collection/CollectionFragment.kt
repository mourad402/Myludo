package fr.app.myludo.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.app.myludo.databinding.FragmentCollectionBinding
import fr.app.myludo.ui.adapters.GamesAdapter
import fr.app.myludo.ui.collection.CollectionViewModel

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
        val partiesViewModel =
            ViewModelProvider(this)[CollectionViewModel::class.java]

        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = partiesViewModel
        binding.recyclerviewCollection.adapter = GamesAdapter ()

        val textView: TextView = binding.textCollection
        partiesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}