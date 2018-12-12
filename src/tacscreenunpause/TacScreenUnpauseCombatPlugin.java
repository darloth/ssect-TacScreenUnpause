package tacscreenunpause;

import com.fs.starfarer.api.combat.BaseEveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatUIAPI;
import com.fs.starfarer.api.input.InputEventAPI;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Darloth
 * Date: 12/12/2018
 * Time: 14:11
 */
public class TacScreenUnpauseCombatPlugin extends BaseEveryFrameCombatPlugin
{
    CombatEngineAPI combatEngine;
    CombatUIAPI combatUI;
    boolean inTacmapLastFrame;

    @Override
    public void init(CombatEngineAPI engine)
    {
        this.combatEngine = engine;
        this.combatUI = engine.getCombatUI();
        inTacmapLastFrame = true;
    }

    @Override
    public void advance(float amount, List<InputEventAPI> events)
    {
        // defensive clauses to make sure that we have a combat UI to work with.
        if(this.combatEngine == null) return;
        if(this.combatUI == null)
        {
            this.combatUI = combatEngine.getCombatUI();
        };

        if(this.combatUI == null) return;


        // store state, last frame and this frame, were we in tac map.
        final boolean inTacmapThisFrame = this.combatUI.isShowingCommandUI();
        if(inTacmapThisFrame && !inTacmapLastFrame)
        {
            combatEngine.setPaused(false);
        }

        inTacmapLastFrame = inTacmapThisFrame;
    }


}
