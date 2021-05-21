package com.jshme.covidwidget.presentation.ui.add

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.DrawableRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.jshme.covidwidget.R
import com.jshme.covidwidget.databinding.DialogSelectIconListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectIconDialog : DialogFragment() {

    interface OnSelectIconDismissListener {
        fun onClickIcon(@DrawableRes drawableRes: Int?)
    }

    private var onDismissListener: OnSelectIconDismissListener? = null

    @DrawableRes
    private var selectedIconRes: Int? = null

    lateinit var binding: DialogSelectIconListBinding
    private val viewModel: AddWidgetViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogSelectIconListBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onDismissListener = activity as? OnSelectIconDismissListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
        initView()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.onClickIcon(selectedIconRes)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), R.style.Theme_AppCompat_Light_Dialog).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    }

    private fun initView() {
        binding.selectButton.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun observeData() {
        viewModel.leftIconChecked.observe(viewLifecycleOwner) { isSelected ->
            if(isSelected) selectedIconRes = R.drawable.ic_happy
        }

        viewModel.rightIconChecked.observe(viewLifecycleOwner) { isSelected ->
            if(isSelected) selectedIconRes = R.drawable.ic_down_arrow
        }
    }
}
