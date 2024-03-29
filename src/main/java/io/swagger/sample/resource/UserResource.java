/**
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.swagger.sample.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.sample.data.UserData;
import io.swagger.sample.exception.ApiException;
import io.swagger.sample.model.User;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
@Path("/user")
@Produces({"application/json", "application/xml"})
public class UserResource {
  static UserData userData = new UserData();

  @POST
  @Operation(summary = "Create user",
    tags = {"users"},
    description = "This can only be done by the logged in user.")
  public Response createUser(
      @Parameter(description = "Created user object", required = true) User user) {
    userData.addUser(user);
    return Response.ok().entity("").build();
  }

  @POST
  @Path("/createWithArray")
  @Operation(summary = "Creates list of users with given input array",
          tags = {"users"})
  public Response createUsersWithArrayInput(@Parameter(description = "List of user object", required = true) User[] users) {
      for (User user : users) {
          userData.addUser(user);
      }
      return Response.ok().entity("").build();
  }

  @POST
  @Path("/createWithList")
  @Operation(summary = "Creates list of users with given input array",
          tags = {"users"})
  public Response createUsersWithListInput(@Parameter(description = "List of user object", required = true) java.util.List<User> users) {
      for (User user : users) {
          userData.addUser(user);
      }
      return Response.ok().entity("").build();
  }

  @PUT
  @Path("/{username}")
  @Operation(summary = "Updated user",
    description = "This can only be done by the logged in user.",
          tags = {"users"},
    responses = {
            @ApiResponse(responseCode = "200", description = "user updated"),
            @ApiResponse(responseCode = "400", description = "Invalid user supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
  public Response updateUser(
      @Parameter(description = "name that need to be deleted", required = true) @PathParam("username") String username,
      @Parameter(description = "Updated user object", required = true) User user) {
    userData.addUser(user);
    return Response.ok().entity("").build();
  }

  @DELETE
  @Path("/{username}")
  @Operation(summary = "Delete user",
    description = "This can only be done by the logged in user.",
          tags = {"users"},
    responses = {
            @ApiResponse(responseCode = "200", description = "user deteled"),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
  public Response deleteUser(
      @Parameter(description = "The name that needs to be deleted", required = true) @PathParam("username") String username) {
    if (userData.removeUser(username)) {
      return Response.ok().entity("").build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @GET
  @Path("/{username}")
  @Operation(summary = "Get user by user",
          tags = {"users"},
    responses = {
            @ApiResponse(description = "The user",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied"),
            @ApiResponse(responseCode = "400", description = "User not found aaa") })
  public Response getUserByName(
      @Parameter(description = "The name that needs to be fetched. Use user1 for testing. ", required = true) @PathParam("username") String username)
    throws ApiException {
      User user = userData.findUserByName(username);
    if (null != user) {
      System.out.println("aaaaaaaaaaaaaaaaa");
      return Response.ok().entity(user).build();
    } else {
      throw new io.swagger.sample.exception.NotFoundException(404, "User not found abc");
    }
  }
  @GET
  @Path("/login")
  @Operation(summary = "Logs user into the system",
          tags = {"users"},
    responses = {
            @ApiResponse(description = "Successfully logged in",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid username/password supplied") })
  public Response loginUser(
      @Parameter(description = "The user name for login", required = true) @QueryParam("username") String username,
      @Parameter(description = "The password for login in clear text", required = true) @QueryParam("password") String password) {
    return Response.ok()
        .entity("logged in user session:" + System.currentTimeMillis())
        .build();
  }

  @GET
  @Path("/logout")
  @Operation(summary = "Logs out current logged in user session",
          tags = {"users"})
  public Response logoutUser() {
    return Response.ok().entity("").build();
  }



  @GET
  @Path("/ccc")
  public void aaa(){

  }
}
