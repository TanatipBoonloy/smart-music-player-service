package kmitl.sp.smp.controller;

import kmitl.sp.smp.model.server.request.LearnDataRequest;
import kmitl.sp.smp.model.server.request.LoginWithFacebookRequest;
import kmitl.sp.smp.model.server.response.base.ApiBaseResponse;
import kmitl.sp.smp.service.ControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * Created by Jo on 3/24/2017.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "api/v1/smp")
public class Controller {
    private final ControllerService controllerService;

    @Inject
    public Controller(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @RequestMapping(value = "health", method = RequestMethod.GET)
    public boolean getHealth() {
        return true;
    }

    @RequestMapping(value = "artists", method = RequestMethod.GET)
    public ApiBaseResponse<?> getAllArtist() {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.getAllArtistName());
    }

    @RequestMapping(value = "song/{songId}", method = RequestMethod.GET)
    public ApiBaseResponse<?> getSongById(@PathVariable String songId) {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.getSongById(songId));
    }

    @RequestMapping(value = "songs", method = RequestMethod.GET)
    public ApiBaseResponse<?> getAllSongs() {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.getAllSong());
    }

    @RequestMapping(value = "songs/{songIds}", method = RequestMethod.GET)
    public ApiBaseResponse<?> getSongByIds(@PathVariable String[] songIds) {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.getSongsByIds(Arrays.asList(songIds)));
    }

    @RequestMapping(value = "songs/random/{quantity}", method = RequestMethod.GET)
    public ApiBaseResponse<?> getRandomSong(@PathVariable Integer quantity) {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.getRandomSongs(quantity));
    }

    @RequestMapping(value = "songs/suggested/{userId}/{qty}", method = RequestMethod.GET)
    public ApiBaseResponse<?> getSuggestedSong(@PathVariable String userId, @PathVariable Integer qty) {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.getSuggestedMusicByUser(userId, qty));
    }

    @RequestMapping(value = "songs/search/{keyword}", method = RequestMethod.GET)
    public ApiBaseResponse<?> searchSongsByKeyword(@PathVariable String keyword) {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.searchSongsByKeyword(keyword));
    }

    @RequestMapping(value = "data/learn/{userId}", method = RequestMethod.POST)
    public ApiBaseResponse<?> learnDate(@PathVariable String userId, @RequestBody LearnDataRequest learnDataRequest) {
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.learnData(userId, learnDataRequest));
    }

    @RequestMapping(value = "login/new", method = RequestMethod.GET)
    public ApiBaseResponse<?> getNewUser(){
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.getNewUserId());
    }

    @RequestMapping(value = "login/facebook", method = RequestMethod.POST)
    public ApiBaseResponse<?> loginWithFacebook(@RequestBody LoginWithFacebookRequest request){
        return new ApiBaseResponse<>(HttpStatus.OK, controllerService.loginWithFacebook(request));
    }
}
