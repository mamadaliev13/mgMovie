package uz.mamadaliev.mimovie

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.common.collect.ImmutableList
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadaliev.mimovie.databinding.FragmentPlayerBinding
import uz.mamadaliev.mimovie.presentation.BaseFragment

@AndroidEntryPoint
class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate),
    Player.Listener {

    private lateinit var player: ExoPlayer
    var VIDEO_URL = "https://www.youtube.com/watch?v=JrorqbFNMF4"
    var url = "https://www.youtube.com/watch?v=JrorqbFNMF4"
    override fun onViewCreate() {
        VIDEO_URL =
            requireArguments().getString("VIDEO_URL", "https://www.youtube.com/watch?v=JrorqbFNMF4")
         url = "www.youtube.com/watch?v=$VIDEO_URL"
        val savedInstanceState: Bundle? = null

        setupPlayer()
        addMP3()
        addMP4Files()


        // restore playstate on Rotation
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("mediaItem") != 0) {
                val restoredMediaItem = savedInstanceState.getInt("mediaItem")
                val seekTime = savedInstanceState.getLong("SeekTime")
                player.seekTo(restoredMediaItem, seekTime)
                player.play()
            }
        }
    }

    private fun addMP4Files() {
        val mediaItem = MediaItem.fromUri(url)
        val mediaItem2 = MediaItem.fromUri(url)
        val newItems: List<MediaItem> = ImmutableList.of(
            mediaItem,
            mediaItem2
        )
        player.addMediaItems(newItems)
        player.prepare()
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        binding.videoView.player = player
        player.addListener(this)
    }

    private fun addMP3() {
        // Build the media item.
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
    }


    override fun onStop() {
        super.onStop()
        player.release()
    }

    override fun onResume() {
        super.onResume()
        setupPlayer()
        addMP3()
        addMP4Files()
    }

    // handle loading
    override fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> {
                binding.progressBar.visibility = View.VISIBLE

            }

            Player.STATE_READY -> {
                binding.progressBar.visibility = View.INVISIBLE
            }

            Player.STATE_ENDED -> {
                Log.d(TAG, "onPlaybackStateChanged: STATE_ENDED")
            }

            Player.STATE_IDLE -> {
                Log.d(TAG, "onPlaybackStateChanged: STATE_IDLE")
            }
        }
    }

    // save details if Activity is destroyed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
        // current play position
        outState.putLong("SeekTime", player.currentPosition)
        // current mediaItem
        outState.putInt("mediaItem", player.currentMediaItemIndex)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}