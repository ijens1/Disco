package me.isaacdjl.disco.IntroTests

import com.google.android.gms.maps.model.LatLng
import me.isaacdjl.disco.data.repository.RepositoryImpl
import me.isaacdjl.disco.ui.intro.IntroViewModel
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class IntroViewModelTest {

    @Mock
    private lateinit var repository: RepositoryImpl

    private lateinit var introViewModel: IntroViewModel

    @Before
    fun setup() {
        introViewModel = IntroViewModel(repository)
    }

    @Test
    fun foodPrefsInsertion() {
        introViewModel.addUserFoodPreference("food1")

        assertThat(introViewModel.retrieveUserFoodPreferences(), `is`(notNullValue()))
        assertThat(introViewModel.retrieveUserFoodPreferences()?.get(0), `is`("food1"))
    }

    @Test
    fun foodPrefsDeletion() {
        introViewModel.addUserFoodPreference("food1")
        introViewModel.addUserFoodPreference("food2")
        introViewModel.addUserFoodPreference("food3")

        introViewModel.removeUserFoodPreference("food3")

        assertThat(introViewModel.retrieveUserFoodPreferences()?.size, `is`(2))

        introViewModel.removeUserFoodPreference("food2")
        introViewModel.removeUserFoodPreference("food1")

        assertThat(introViewModel.retrieveUserFoodPreferences(), `is`(nullValue()))
    }

    @Test
    fun locationPrefChanges() {
        val location = LatLng(0.0, 0.0)

        introViewModel.changeUserLocationPreference(location)

        assertThat(introViewModel.retrieveUserLocationPreference(), `is`(location))
    }

    @Test
    fun addingEatTimeWithNoCurrentDateDoesNothing() {
        introViewModel.addUserEatTime(0, 0)

        assertThat(introViewModel.retrieveUserEatDates(), `is`(nullValue()))
    }

    @Test
    fun addingEatTimeWithCurrentDate() {
        val currDateSelected = Calendar.getInstance()

        val newEatTime = Calendar.getInstance()
        newEatTime.set(currDateSelected.get(Calendar.YEAR) - 1900, currDateSelected.get(Calendar.MONTH), currDateSelected.get(Calendar.DAY_OF_MONTH), 0, 0)

        introViewModel.setCurrentDateSelected(currDateSelected)

        introViewModel.addUserEatTime(0, 0)

        val userEats = ArrayList<Calendar>()
        userEats.add(newEatTime)

        assertThat(introViewModel.retrieveUserEatDates()?.contains(userEats), `is`(true))
    }
}