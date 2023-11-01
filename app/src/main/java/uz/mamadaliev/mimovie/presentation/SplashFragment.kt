package uz.mamadaliev.mimovie.presentation

import android.os.CountDownTimer
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadaliev.mimovie.R
import uz.mamadaliev.mimovie.databinding.FragmentSplashBinding

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun onViewCreate() {
        object : CountDownTimer(2000, 100) {
            override fun onFinish() {
                navController.navigate(R.id.action_splashFragment_to_navigation_home)
            }

            override fun onTick(value: Long) {

            }
        }.start()
    }
}