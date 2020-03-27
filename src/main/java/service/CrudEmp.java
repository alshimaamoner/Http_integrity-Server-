package service;
import java.util.List; // import just the List interface
import java.util.ArrayList; // import just the ArrayList class
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
@Path("/employees")
public class CrudEmp {
    Dao dao=new Dao();
      
     @POST
       public void addUser(User user,@Context HttpServletRequest request){
        dao.insertUser(user);
        System.out.println("Employee Created...");
        System.out.println(request.getSession().getId());   

        /*
        if(flag){
            return "row add";
        }else{
            return "no row added";
        } 
        */
    }
    
     @GET
     @Path("retrieve/{id}")
     @Produces({MediaType.APPLICATION_JSON})
    public User showUser(@PathParam("id")int id,@Context UriInfo ui,@Context HttpHeaders hh,@Context HttpServletRequest request){
        
        return dao.showUsers(id);
            
    }
    
     @GET
     @Produces({MediaType.APPLICATION_JSON})
    public List<User> showAllUser(@Context UriInfo ui,@Context HttpHeaders hh,@Context HttpServletRequest request){
         System.out.println(request.getSession().getId());   
         System.out.println(request.getSession().getAttribute("name"));
        MultivaluedMap<String,String> headerParam=hh.getRequestHeaders();
        System.out.println(headerParam.get("Accept"));
        System.out.println(ui.getAbsolutePath());

        return dao.showAllUsers();
            
    }
    
     @PUT
      @Produces(MediaType.TEXT_PLAIN)
      @Consumes(MediaType.APPLICATION_JSON)
     public Response updateUser(User user,@Context HttpServletRequest request){
        dao.updateUser(user);
        System.out.println(request.getSession().getId());   

         return Response.noContent().build();
        /*if(flag){
            return "row updated";
        }else{
            return "no row update";
        } 
        */
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id")int id,@Context HttpServletRequest request){
        boolean flag=dao.deleteUser(id);
        System.out.println(request.getSession().getId());   

        return Response.status(202).entity("User deleted successfully !!").build();
       /* if(flag){
            return "row add";
        }else{
            return "no row added";
        } 
        */
    }
}
    

