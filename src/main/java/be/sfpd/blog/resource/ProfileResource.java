package be.sfpd.blog.resource;

import be.sfpd.blog.model.Profile;
import be.sfpd.blog.service.ProfileService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("profiles")
public class ProfileResource {

    ProfileService profileService = new ProfileService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getAllProfiles() {
        return profileService.getProfiles();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfileByName(@PathParam("name") String name) {
        return profileService.getProfileByName(name);
    }
}
