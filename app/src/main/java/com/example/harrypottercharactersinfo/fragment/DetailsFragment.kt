package com.example.harrypottercharactersinfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import com.example.harrypottercharactersinfo.R
import com.example.harrypottercharactersinfo.adapter.CharacterAdapter
import com.example.harrypottercharactersinfo.databinding.FragmentDetailsBinding
import com.example.harrypottercharactersinfo.model.HPCharacterDetails
import com.example.harrypottercharactersinfo.retrofit.HPService
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding) {
        "View was destroyed"
    }

    private val adapter = CharacterAdapter

    val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailsBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDetails()

        with(binding) {
            toolbar.setupWithNavController(findNavController())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun loadDetails() {
        HPService.hpApi.getCharacterDetails()
            .enqueue(object : Callback<List<HPCharacterDetails>> {

                override fun onResponse(
                    call: Call<List<HPCharacterDetails>>,
                    response: Response<List<HPCharacterDetails>>
                ) {
                    if(response.isSuccessful) {
                        val characterDetailsList = response.body() ?: return
                        val characterDetails = characterDetailsList.find { it.name == args.name }
                        with(binding) {

                            characterImage.load(characterDetails?.imageUrl) {
                                scale(Scale.FIT)
                                size(ViewSizeResolver(root))
                            }

                            fullName.text = characterDetails?.name
                            house.text = characterDetails?.house

                            actor.text = "Actror: " + characterDetails?.actor
                            species.text = "Species: " + characterDetails?.species
                            gender.text = "Gender: " + characterDetails?.actor
                            dateOfBirth.text = "Date of birth: " + characterDetails?.dateOfBirth
                            patronus.text = "Patronus: " + characterDetails?.patronus
                        }
                    }
                }

                override fun onFailure(call: Call<List<HPCharacterDetails>>, t: Throwable) {
                    handleErrors(t.message ?: DetailsFragment.GENERAL_ERROR_MESSAGE)
                }
            })
    }


    private fun handleErrors(errorMessage: String) {
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT)
            .setAction(android.R.string.ok) {}
            .show()
    }

    companion object {

        private const val GENERAL_ERROR_MESSAGE = "Something went wrong"
    }
}