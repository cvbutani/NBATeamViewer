package com.chirag.sonic.data.network;

import com.chirag.sonic.Constant;
import com.chirag.sonic.data.model.NBATeam;
import com.chirag.sonic.data.model.TeamPlayer;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Chirag on 4/1/2019 at 19:43.
 * Project - NBATeamViewer
 */
public class NBADeserializer implements JsonDeserializer<NBATeam> {

    @Override
    public NBATeam deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException {

        return parseObject(json.getAsJsonObject());
    }

    private NBATeam parseObject(JsonObject object) {

        NBATeam nbaTeam = new NBATeam();
        JsonElement winsElement = object.get(Constant.WINS);
        nbaTeam.wins = getIntOrZero(winsElement);

        JsonElement losesElement = object.get(Constant.LOSSES);
        nbaTeam.losses = getIntOrZero(losesElement);

        JsonElement fullNameElement = object.get(Constant.FULL_NAME);
        nbaTeam.full_name = getStringOrEmpty(fullNameElement);

        JsonArray teamObject = object.getAsJsonArray(Constant.PLAYERS);
        for (int i = 0; i < teamObject.size(); i++) {

            if (teamObject.get(i) != null) {
                TeamPlayer teamPlayer = new TeamPlayer();
                JsonElement firstNameElement = teamObject.get(i).getAsJsonObject().get(
                        Constant.FIRST_NAME);
                teamPlayer.first_name = getStringOrEmpty(firstNameElement);

                JsonElement lastNameElement =
                        teamObject.get(i).getAsJsonObject().get(Constant.LAST_NAME);
                teamPlayer.last_name = getStringOrEmpty(lastNameElement);
                JsonElement positionElement =
                        teamObject.get(i).getAsJsonObject().get(Constant.POSITION);

                teamPlayer.position = getStringOrEmpty(positionElement);
                JsonElement numberElement =
                        teamObject.get(i).getAsJsonObject().get(Constant.NUMBER);

                teamPlayer.number = getIntOrZero(numberElement);
                nbaTeam.players.add(teamPlayer);
            }
        }
        return nbaTeam;
    }

    private int getIntOrZero(JsonElement element) {
        return element != null ? element.getAsInt() : 0;
    }

    private String getStringOrEmpty(JsonElement element) {
        return element != null ? element.getAsString() : "";
    }
}
