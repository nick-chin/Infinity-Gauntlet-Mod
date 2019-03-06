package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpireInitializer
public class InfinityGauntletUnpowered extends CustomRelic {
    public static final String ID = "InfinityGauntletUnpowered";
    public static final String IMG = "images/relics/InfinityGauntletUnpowered.png";
    public static final int STR_AMT = 2;


    public InfinityGauntletUnpowered() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.SHOP, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, STR_AMT), STR_AMT));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STR_AMT + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new InfinityGauntletUnpowered();
    }
}
