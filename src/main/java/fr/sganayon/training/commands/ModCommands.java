package fr.sganayon.training.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import fr.sganayon.training.McTrainingMod;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> cmdTut = dispatcher.register(
                Commands.literal(McTrainingMod.MODID)
                        .then(CommandTest.register(dispatcher))
                        .then(CommandSpawner.register(dispatcher))
                        .then(CommandTpDim.register(dispatcher))
        );

        dispatcher.register(Commands.literal("mtm").redirect(cmdTut));
    }

}
