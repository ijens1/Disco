package me.isaacdjl.disco.ui.intro

import android.graphics.drawable.Drawable
import android.net.Uri
import com.pchmn.materialchips.model.ChipInterface

/**
 * Chip which will allow users to enter the types of ethnic restaurants they would like to explore
 *
 * @author Isaac Jensen-Large
 */

class FoodPreferenceChip(val id: Int, val name: String) : ChipInterface {

    override fun getId(): Any {
        return id;
    }

    override fun getLabel(): String {
        return name;
    }

    /**
     * Possible cause for NPEs here, although I've set the chips input to not use an avatar icon
    */
    override fun getAvatarDrawable(): Drawable? {
        return null
    }

    /**
     * See message above previous function about possible NPEs
     */
    override fun getAvatarUri(): Uri {
        return Uri.EMPTY;
    }

    override fun getInfo(): String {
        return "Add this to explore $name restaurants";
    }
}