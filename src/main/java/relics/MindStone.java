package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;

public class MindStone extends CustomRelic {
    public static final String ID = "MindStone";
    public static final String IMG = "localization/images/relics/MindStone.png";
    public static final int HEAL_AMT = 10;
    public static final int ARTIFACT_AMT = 1;
    private boolean firstTurn = true;

    public MindStone() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0] + HEAL_AMT + this.DESCRIPTIONS[1];
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if ((room instanceof RestRoom))
        {
            flash();
            AbstractDungeon.player.heal(HEAL_AMT);
            this.counter = -2;
            this.pulse = true;
        }
    }

    @Override
    public void atPreBattle() {
        this.firstTurn = true;
    }

    @Override
    public void setCounter(int counter) {
        super.setCounter(counter);
        if (counter == -2) {
            this.pulse = true;
        }
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            if (this.counter == -2) {
                this.pulse = false;
                this.counter = -1;
                flash();
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new ArtifactPower(AbstractDungeon.player, ARTIFACT_AMT), ARTIFACT_AMT));
            }
        }
        this.firstTurn = false;
    }


    @Override
    public AbstractRelic makeCopy() {
        return new MindStone();
    }
}
