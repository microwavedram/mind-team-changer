package example;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.net.Administration.*;
import mindustry.world.blocks.storage.*;

public class ExamplePlugin extends Plugin{

    public boolean teamsLocked = false;

    //called when game initializes
    @Override
    public void init(){
        Call.sendMessage("[scarlet] hi guys");
    }

    @Override
    public void registerServerCommands(CommandHandler handler){
        handler.register("teamlock", "Toggle Team Lock", args -> {
            teamsLocked = !teamsLocked;
        });
    }

    //register commands that player can invoke in-game
    @Override
    public void registerClientCommands(CommandHandler handler){

        //register a simple reply command
        handler.<Player>register("team", "<team>", "A command to change your team", (args, player) -> {

            if (teamsLocked) {
                player.sendMessage("[scarlet] Teams are currently locked");
                return;
            }

            Team target;

            switch (args[0].toLowerCase()){
                case "derelict":
                case "black":
                    target = Team.derelict;
                    break;
                case "sharded":
                case "yellow":
                    target = Team.sharded;
                    break;
                case "crux":
                case "red":
                    target = Team.crux;
                    break;
                case "malis":
                case "purple":
                    target = Team.purple;
                    break;
                case "green":
                    target = Team.green;
                    break;
                case "blue":
                    target = Team.blue;
                    break;
                default:
                    player.sendMessage("[scarlet] No team with that name found");
                    return;
            }

            player.team(target);
        });
    }
}

