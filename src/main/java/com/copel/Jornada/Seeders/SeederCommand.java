package com.copel.Jornada.Seeders;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SeederCommand implements ApplicationRunner {

    private final UserSeedCommand userSeedCommand;
    private final ProblemaSeedCommand problemaSeedCommand;
    private final RegiaoSeedCommand regiaoSeedCommand;

    public SeederCommand(UserSeedCommand userSeedCommand, ProblemaSeedCommand problemaSeedCommand, RegiaoSeedCommand regiaoSeedCommand) {
        this.userSeedCommand = userSeedCommand;
        this.problemaSeedCommand = problemaSeedCommand;
        this.regiaoSeedCommand = regiaoSeedCommand;
    }

    @Override
    public void run(ApplicationArguments args) {
        userSeedCommand.seedAdminUser();
        problemaSeedCommand.seedProblemas();
        regiaoSeedCommand.seedRegiao();
    }

}
