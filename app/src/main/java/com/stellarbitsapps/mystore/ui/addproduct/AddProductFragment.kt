package com.stellarbitsapps.mystore.ui.addproduct

import android.app.Dialog
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stellarbitsapps.mystore.R
import com.stellarbitsapps.mystore.databinding.FragmentAddProductBinding
import com.stellarbitsapps.mystore.util.CurrencyTextWatcher

class AddProductFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageUri = uri
            binding.imageProduct.setImageURI(uri)
        }

    private val viewModel: AddProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setContentView(R.layout.fragment_add_product)

        dialog.behavior.peekHeight = resources.displayMetrics.heightPixels
        dialog.behavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
        }

        return dialog
    }

    private fun setListeners() {
        binding.imageProduct.setOnClickListener {
            chooseImage()
        }

        binding.buttonAddProduct.setOnClickListener {
            val description = binding.editTextInputDescription.text.toString()
            val price = binding.editTextInputPrice.text.toString()
        }

        binding.editTextInputDescription.run {
            setOnFocusChangeListener { _, hasFocus ->
                binding.textViewDescriptionTitle.visibility =
                    if (hasFocus) View.VISIBLE else View.INVISIBLE
            }
        }

        binding.editTextInputPrice.run {
            addTextChangedListener(CurrencyTextWatcher(this))

            setOnFocusChangeListener { _, hasFocus ->
                binding.textViewValueTitle.visibility =
                    if (hasFocus) View.VISIBLE else View.INVISIBLE
            }
        }
    }

    private fun chooseImage() {
        getContent.launch("image/*")
    }
}