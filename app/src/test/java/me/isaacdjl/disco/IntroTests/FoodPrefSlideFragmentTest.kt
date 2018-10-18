package me.isaacdjl.disco.IntroTests

import me.isaacdjl.disco.ViewModelFactory
import me.isaacdjl.disco.ui.intro.FoodPrefSlideFragment
import me.isaacdjl.disco.ui.intro.IntroViewModel
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FoodPrefSlideFragmentTest {

    @Mock
    lateinit var introViewModel: IntroViewModel

    @InjectMocks
    var foodPrefSlideFragment = FoodPrefSlideFragment()

    @Test
    fun cantGoForwardWhenPrefsEmpty() {
        `when`(introViewModel.retrieveUserFoodPreferences()).thenReturn(null)

        val canGoForward = foodPrefSlideFragment.canGoForward()

        assertThat(canGoForward, `is`(false))
    }

    @Test
    fun canGoForwardWhenPrefsNotEmpty() {
        `when`(introViewModel.retrieveUserFoodPreferences()).thenReturn(ArrayList())

        val canGoForward = foodPrefSlideFragment.canGoForward()

        assertThat(canGoForward, `is`(true))
    }
}