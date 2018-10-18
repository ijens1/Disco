package me.isaacdjl.disco.IntroTests

import com.google.android.gms.maps.model.LatLng
import me.isaacdjl.disco.ui.intro.IntroViewModel
import me.isaacdjl.disco.ui.intro.LocationPrefSlideFragment
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationPrefSlideFragmentTest {

    @Mock
    lateinit var introViewModel: IntroViewModel

    @InjectMocks
    lateinit var locationPrefSlideFragment: LocationPrefSlideFragment

    @Test
    fun cantGoForwardWhenPrefEmpty() {
        `when`(introViewModel.retrieveUserLocationPreference()).thenReturn(null)

        val canGoForward = locationPrefSlideFragment.canGoForward()

        assertThat(canGoForward, `is`(false))
    }

    @Test
    fun canGoForwardWhenPrefNotEmpty() {
        `when`(introViewModel.retrieveUserLocationPreference()).thenReturn(LatLng(0.0, 0.0))

        val canGoForward = locationPrefSlideFragment.canGoForward()

        assertThat(canGoForward, `is`(true))
    }
}