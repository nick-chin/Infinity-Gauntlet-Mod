package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class TimeStone extends CustomRelic {
    public static final String ID = "TimeStone";
    public static final String IMG = "localization/images/relics/TimeStone.png";
    private static final int ENERGY_AMT = 1;
    private boolean firstTurn = true;

    public TimeStone() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.COMMON, LandingSound.MAGICAL);
        this.energyBased = true;
    }

    public String getUpdatedDescription()
    {
        if (AbstractDungeon.player != null) {
            return setDescription(AbstractDungeon.player.chosenClass);
        }
        return setDescription(null);
    }

    private String setDescription(AbstractPlayer.PlayerClass c)
    {
        return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1] + LocalizedStrings.PERIOD;
    }

    public void updateDescription(AbstractPlayer.PlayerClass c)
    {
        this.description = setDescription(c);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    public void atPreBattle()
    {
        this.firstTurn = true;
    }

    public void atTurnStart()
    {
        if (this.firstTurn)
        {
            flash();
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(ENERGY_AMT));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.firstTurn = false;
        }
    }

    public AbstractRelic makeCopy()
    {
        return new TimeStone();
    }
}
