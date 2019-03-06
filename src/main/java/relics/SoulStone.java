package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SoulStone extends CustomRelic {
    public static final String ID = "SoulStone";
    public static final String IMG = "images/relics/SoulStone.png";
    public static final int VUL_AMT = 2;

    public SoulStone(){
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(mo, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player,
                    new WeakPower(mo, VUL_AMT, false), VUL_AMT, true));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1] + VUL_AMT + DESCRIPTIONS[2];
    }

    @Override
    public boolean canSpawn() {
        return (AbstractDungeon.player.hasRelic("Burning Blood")) ||
                (AbstractDungeon.player.hasRelic("Ring of the Snake")) ||
                (AbstractDungeon.player.hasRelic("Cracked Core"));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SoulStone();
    }
}
