import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.RelicStrings;
import relics.*;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class InfinityGauntletMod implements EditRelicsSubscriber, EditStringsSubscriber {


    public InfinityGauntletMod() {
        BaseMod.subscribe(this);
    }

    public static void initialize(){
        InfinityGauntletMod infinityGauntletMod = new InfinityGauntletMod();
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new InfinityGauntletUnpowered(), RelicType.SHARED);
        BaseMod.addRelic(new SoulStone(), RelicType.SHARED);
        BaseMod.addRelic(new TimeStone(), RelicType.SHARED);
        BaseMod.addRelic(new SpaceStone(), RelicType.SHARED);
        BaseMod.addRelic(new MindStone(), RelicType.SHARED);
        BaseMod.addRelic(new PowerStone(), RelicType.SHARED);
        BaseMod.addRelic(new RealityStone(), RelicType.SHARED);
        BaseMod.addRelic(new InfinityGauntlet(), RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings() {
        String relicStrings = Gdx.files.internal("localization/relicStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
    }
}
