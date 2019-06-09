package com.users.boundary;

import com.users.control.UsersService;
import com.users.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author nkengasong
 */
@Path("users")
@Api(value = "users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    private UsersService usersService;

    @GET
    @ApiOperation(value = "Get all users")
    public Response getAllUsers() {
        return Response.ok(usersService.getAll()).build();
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "Get a user by id", response = User.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Invalid user input"),
        @ApiResponse(code = 404, message = "User not found")
    })
    public Response getOneUser(@ApiParam(value = "ID of the user that is fetched", required = true)
            @PathParam("id") Long id) {
        final Optional<User> found = usersService.getOne(id);
        return found.isPresent() ? Response.ok(found.get()).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @ApiOperation(value = "Add a new user")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid user input"),
        @ApiResponse(code = 201, message = "User successfully added")
    })
    public Response addUser(@ApiParam(value = "User that will be added", required = true) User user, @Context UriInfo uriInfo) {
        usersService.add(user);
        return Response.created(getLocation(uriInfo, user.getId())).build();
    }

    @PUT
    @Path("{id}")
    @ApiOperation(value = "Update existing user", response = User.class)
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid user input"),
        @ApiResponse(code = 404, message = "User not found"),
        @ApiResponse(code = 200, message = "User updated")
    })
    public Response updateUser(
            @ApiParam(value = "ID of the user that is to be updated", required = true) @PathParam("id") Long id,
            @ApiParam(value = "User that needs to be updated", required = true) User user) {
        user.setId(id);
        boolean result = usersService.update(user);
        return result ? Response.ok(user).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    URI getLocation(UriInfo uriInfo, Long id) {
        return uriInfo.getAbsolutePathBuilder().path("" + id).build();
    }
}
