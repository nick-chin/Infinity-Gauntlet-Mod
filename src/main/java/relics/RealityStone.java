package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RealityStone extends CustomRelic {
    public static final String ID = "RealityStone";
    public static final String IMG = "localization/images/relics/RealityStone.png";
    public static final int INTANGIBLE_AMT = 1;
    public static final int NUM_TURNS = 4;

    public RealityStone() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            this.counter += 1;
        }
        if (this.counter == NUM_TURNS) {
            this.counter = 0;
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, null, new IntangiblePlayerPower(AbstractDungeon.player, INTANGIBLE_AMT), INTANGIBLE_AMT));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RealityStone();
    }
}
