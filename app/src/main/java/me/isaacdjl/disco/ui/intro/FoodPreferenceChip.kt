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

    override fun getAvatarDrawable(): Drawable {
        return Drawable.createFromPath(null);
    }

    override fun getAvatarUri(): Uri {
        return Uri.EMPTY;
    }

    override fun getInfo(): String {
        return "Add this to explore $name restaurants";
    }
}