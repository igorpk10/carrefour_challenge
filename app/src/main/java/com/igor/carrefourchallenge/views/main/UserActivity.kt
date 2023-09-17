package com.igor.carrefourchallenge.views.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.search.SearchView.TransitionState
import com.igor.carrefourchallenge.databinding.ActivityMainBinding
import com.igor.carrefourchallenge.domain.models.User
import com.igor.carrefourchallenge.views.extensions.visible
import com.igor.carrefourchallenge.views.main.details.UserDetailsBottomSheetFragment
import com.igor.carrefourchallenge.views.main.states.UserActivityUiState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: UserViewModel by viewModels()

    private val adapter: UsersAdapter by lazy {
        UsersAdapter {
            inflateBottomSheet(it)
        }
    }

    private var bottomSheet: UserDetailsBottomSheetFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    override fun onDestroy() {
        bottomSheet?.dismiss()
        super.onDestroy()
    }

    private fun initViews() {
        initSearch()
        initRecycler()
        initRetryButton()
        initObservables()

        viewModel.getUsers()
    }

    private fun initSearch() {
        binding.successState.catSearchView.apply {
            getEditText()
                .setOnEditorActionListener { v, actionId, event ->
                    binding.successState.catSearchBar.text = v.text

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        viewModel.filterByName(v.text.toString())
                    }

                    hide()
                    false
                }
        }

        val onbackCallback = object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                binding.successState.catSearchView.hide()
            }

        }

        this@UserActivity.onBackPressedDispatcher.addCallback(
            this@UserActivity,
            onbackCallback
        )

        binding.successState.catSearchView.addTransitionListener { searchView1, previousState, newState ->
            onbackCallback.isEnabled = newState == TransitionState.SHOWN
        }
    }

    private fun initRecycler() {
        binding.successState.recycler.adapter = adapter
    }

    private fun initRetryButton() {
        binding.errorState.retryButton.setOnClickListener {
            viewModel.getUsers()
        }
    }

    private fun initObservables() {
        viewModel.state.observe(this) {
            when (it) {
                is UserActivityUiState.onError -> setErrorGetUsersState()
                UserActivityUiState.onLoading -> setLoadingState()
                is UserActivityUiState.onSuccess -> setSuccessGetUsersState(it.users)
                is UserActivityUiState.onSearchError -> Toast.makeText(
                    this,
                    it.error,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setLoadingState() {
        binding.loadingState.layout.visible(true)
        binding.errorState.layout.visible(false)
        binding.successState.layout.visible(false)
    }

    private fun setSuccessGetUsersState(users: List<User>) {
        binding.loadingState.layout.visible(false)
        binding.errorState.layout.visible(false)
        binding.successState.layout.visible(true)

        adapter.users = users
    }

    private fun setErrorGetUsersState() {
        binding.loadingState.layout.visible(false)
        binding.errorState.layout.visible(true)
        binding.successState.layout.visible(false)
    }

    private fun inflateBottomSheet(user: User) {
        bottomSheet = UserDetailsBottomSheetFragment.create(user)
        bottomSheet?.show(supportFragmentManager, null)
    }
}