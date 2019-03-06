package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.List;

public class SpaceStone extends CustomRelic {
    public static final String ID = "SpaceStone";
    public static final String IMG = "images/relics/SpaceStone.png";
    public static List<String> MODS;

    public SpaceStone() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.RARE, LandingSound.MAGICAL);
        MODS.add("Flight");
    }

    @Override
    public void onEquip() {
        ModHelper.setMods(MODS);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SpaceStone();
    }
}
