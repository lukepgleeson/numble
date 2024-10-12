package online.glees.numble.utils;

import java.util.List;

import org.mapstruct.Mapper;

import online.glees.numble.model.Guess;
import online.glees.numble.service.Group;

@Mapper(componentModel = "spring")
public interface GameMapper {
    List<Guess> groupToGuess(List<Group> group);
    List<Group> guessToGroup(List<Guess> guess);
}
