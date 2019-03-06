package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class InfinityGauntlet extends CustomRelic {
    public static final String ID = "InfinityGauntlet";
    public static final String IMG = "images/relics/InfinityGauntlet.png";

    public InfinityGauntlet() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        flash();
        int num_of_monsters = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (m.type == AbstractMonster.EnemyType.ELITE || m.type == AbstractMonster.EnemyType.BOSS) {
                m.currentHealth /= 2;
                m.healthBarUpdatedEvent();
            } else if (AbstractDungeon.getCurrRoom().monsters.monsters.indexOf(m) > num_of_monsters / 2) {
                m.currentHealth = 0;
                m.healthBarUpdatedEvent();
            }
        }
    }

    @Override
    public boolean canSpawn() {
        return (AbstractDungeon.player.hasRelic("InfinityGauntletUnpowered")) &&
                (AbstractDungeon.player.hasRelic("SoulStone")) &&
                (AbstractDungeon.player.hasRelic("TimeStone")) &&
                (AbstractDungeon.player.hasRelic("SpaceStone")) &&
                (AbstractDungeon.player.hasRelic("MindStone")) &&
                (AbstractDungeon.player.hasRelic("RealityStone")) &&
                (AbstractDungeon.player.hasRelic("PowerStone"));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new InfinityGauntlet();
    }
}
