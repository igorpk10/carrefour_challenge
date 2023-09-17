package com.igor.carrefourchallenge.views.main.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.igor.carrefourchallenge.databinding.FragmentRepositoriesDialogListDialogBinding
import com.igor.carrefourchallenge.domain.models.User
import com.igor.carrefourchallenge.domain.models.UserDetails
import com.igor.carrefourchallenge.views.extensions.visible
import com.igor.carrefourchallenge.views.main.states.UserDetailsBottomSheetUiState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailsBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {

        private const val ARG_DETAILS = "arg_details"

        fun create(user: User) = UserDetailsBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_DETAILS, user)
            }
        }
    }

    private var _binding: FragmentRepositoriesDialogListDialogBinding? = null

    private val binding get() = _binding!!

    private val adapter: UserRepositoryAdapter by lazy {
        UserRepositoryAdapter {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(it.url)
            startActivity(i)
        }
    }

    private val viewModel: UserDetailsBottomSheetViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentRepositoriesDialogListDialogBinding.inflate(inflater, container, false).apply {
            _binding = this
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservers()
        setLoadingState()

        arguments?.getParcelable<User>(ARG_DETAILS)?.let {
            viewModel.getUserDetails(it.login)
        } ?: kotlin.run {
            Toast.makeText(
                requireContext(),
                "Não conseguirmos recuperar as informações do usuário",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun initViews() {
        binding.successState.recycler.adapter = adapter
    }

    private fun initObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UserDetailsBottomSheetUiState.onError -> setError()
                UserDetailsBottomSheetUiState.onLoading -> setLoadingState()
                is UserDetailsBottomSheetUiState.onSuccess -> setSuccessState(it.user)
            }
        }
    }

    private fun setLoadingState() {
        binding.loadingState.layout.visible(true)
        binding.successState.list.visible(false)
    }

    private fun setError() {
        Toast.makeText(
            requireContext(),
            "Ops, tivemos um erro com esse usuário. Tente novamente!!",
            Toast.LENGTH_LONG
        ).show()

        dismiss()
    }

    private fun setSuccessState(user: UserDetails) {
        binding.loadingState.layout.visible(false)
        binding.successState.list.visible(true)

        binding.successState.apply {
            if (user.company.isNullOrBlank()) {
                this.txtCompany.visible(false)
                this.company.visible(false)
            } else {
                this.company.text = user.company
            }

            if (user.location.isNullOrBlank()) {
                this.txtLocation.visible(false)
                this.location.visible(false)
            } else {
                this.location.text = user.location
            }

            this.followers.text = user.followers.toString()
            adapter.repositories = user.repositories
        }
    }

}
