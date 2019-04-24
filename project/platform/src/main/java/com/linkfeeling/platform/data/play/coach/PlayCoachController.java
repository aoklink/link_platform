package com.linkfeeling.platform.data.play.coach;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.util.ResponseUtil;
import com.linkfeeling.platform.data.play.bean.GymPlayCoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platform/gym/play/coach")
public class PlayCoachController {

    @Autowired
    private PlayCoachComponent playCoachComponent;

    @PostMapping(ControllerActionContract.OPERATE.ADD)
    public Response add(@RequestBody GymPlayCoach gymPlayCoach){
        try {
            return ResponseUtil.newSuccess(playCoachComponent.save(gymPlayCoach));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response update(@RequestBody GymPlayCoach gymPlayCoach){
        try {
            return ResponseUtil.newSuccess(playCoachComponent.save(gymPlayCoach));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.DELETE)
    public Response delete(@RequestBody GymPlayCoach gymPlayCoach){
        try {
            playCoachComponent.delete(gymPlayCoach.getId());
            return ResponseUtil.newSuccess("OK");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }

    @PostMapping(ControllerActionContract.OPERATE.LIST)
    public Response list(@RequestBody GymPlayCoach gymPlayCoach){
        try {
            return ResponseUtil.newSuccess(playCoachComponent.findAllByGymId(gymPlayCoach.getGymId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }
}
