function login() {
    //alert("login");
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    /*let employee = {
        username: username,
        password: password
    }*/

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
        
        if(this.readyState == 4 && this.status == 200){
            let response = this.responseText;
        if(this.responseText.length != 0){
            let parsedata = JSON.parse(this.responseText);
            //alert("Parsedata: " +parsedata);
           var userdetails = {
                id: parsedata.id,
                role_name: parsedata.role_name,
                distributor_id: parsedata.distributor_id
            }
            let jsonObj = JSON.stringify(userdetails);
            sessionStorage.myObject = jsonObj;
            //alert(" sessionStorage: "+sessionStorage.myObject);
            document.getElementById("alert").innerHTML = " ";
            window.location.assign("login.html");
            }
         else{
                document.getElementById("alert").innerHTML = "UserName or Password Not Exists";
            }
        }
    }

    //let url = "http://localhost:8080/product/" + productId.value

    if((username.length == 0) && (password.length == 0))
{
    document.getElementById("alert").innerHTML = "Please Enter Username and Password";
}
else{
    document.getElementById("alert").innerHTML = " ";
    let url = "http://localhost:8080/users/login/" +username+ "/" +password;
    //alert(url);
        xhttp.open("POST", url, true);
    
    // xhttp.setRequestHeader("Content-Type", "application/json");
        //xhttp.setRequestHeader("Access-Control-Allow-Origin","*");
        //xhttp.setRequestHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");

//alert("Header set");
xhttp.send();
}
   // xhttp.send(JSON.stringify(employee));
}

function loggedIn(){
    loggedIn = true;
    if(loggedIn){
       myObject = JSON.parse(sessionStorage.myObject);
        //alert("Session Object: "+myObject);
        //alert("myObject: " +myObject.id);
        window.id = myObject.id;
        window.rolename = myObject.role_name;
        window.distributor_id = myObject.distributor_id;
        //alert(" window values "+ window.id + " " + window.rolename + " " + window.distributor_id);

        if(window.rolename == "Distributor"){
            document.getElementById("disorddetails").style.display = "block";
            document.getElementById("orderdetails").style.display = "block";
        }
        else{
            document.getElementById("add").style.display = "block";
            document.getElementById("place").style.display = "block";
            document.getElementById("manage").style.display = "block";
            document.getElementById("warehouse").style.display = "block";
        }

        //alert(window.id +" "+  window.name +" "+ window.designation +" "+ window.supervisorid);

    }

document.getElementById("welcome").innerHTML = "Welcome "+window.rolename;
}
function clearvalues(){
    document.getElementById("name").value = "";
    document.getElementById("address").value = "";
    document.getElementById("phnum").value = "";
    document.getElementById("username").value = "";
    document.getElementById("userpass").value = "";
}
function validatephno()
{
    //alert("validatephno");
    var inputtxt = document.getElementById("phnum").value;
    var regex=/^[0-9]+$/;
    if (inputtxt.match(regex))
    {
        document.getElementById("errmsg").innerHTML = " ";
    }
    else{
        document.getElementById("errmsg").innerHTML="Enter Valid Phone Numbers";
    }
}

function validateuser(){
    //alert("validateuser");
    let username = document.getElementById("username").value;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        
        if(this.readyState == 4 && this.status == 200){
            let response = this.responseText;
           // alert("response: "+response);
            if(response.length != 0)
            {
                document.getElementById("errmsg").innerHTML = "Username already Exxists. Please select new one.";
            }
            else{
                document.getElementById("errmsg").innerHTML = " ";
            }
        }
    }
    
    let url = "http://localhost:8080/users/validateuser/" +username;
    //alert(url);
        xhttp.open("POST", url, true);
      
   xhttp.send();
}
function viewdistributor(){
    //alert("view distributor");
   // alert("new");
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            //alert(this.responseText)
            let result = JSON.parse(this.responseText);
            //alert("result: "+result);
            if(result != null){
                var row ="";
                document.getElementById("viewUsers").style.display = "block";
                var reslength = Object.keys(result).length;
             for(let i = 0; i < reslength; i++){
                     row += "<tr>";
                     row += "<td>"+result[i]["id"]+"</td>";
                     row += "<td>"+result[i]["name"]+"</td>";
                     row += "<td>"+result[i]["address"]+"</td>";
                     row += "<td>"+result[i]["phone_number"]+"</td>";
                     //row += "<td><input type=radio name='edit' id='editopt'/></td>"
                    // row += "<td><input type=radio name='delete' id='deleteopt'/></td>"
                    // }
                     row += "</tr>";
                     document.getElementById("myTableRows").innerHTML = row;
                  }
                }
    

               
    }
}
let url = "http://localhost:8080/distributor";
//alert("URL:"+url);
xhttp.open("GET", url, true);
xhttp.setRequestHeader('Content-Type', 'application/json');
xhttp.send();
    }
    //}


function adddistributor(){
   // alert("inside adddistributor");
    
    let xhttp = new XMLHttpRequest();

    let distname = document.getElementById("name").value;
    let address = document.getElementById("address").value;
    let phoneno = document.getElementById("phnum").value;
    let username = document.getElementById("username").value;
    let userpass = document.getElementById("userpass").value;
    if((distname == "") || (address == "") || (phoneno == "") || (username == "") || (userpass == "")){
        //alert("Empty Values");
        document.getElementById("errmsg").innerHTML = "Please Enter all the Values";
    }
    else{
        document.getElementById("errmsg").innerHTML = " ";
        clearvalues();
    let userdetails = {
             "role_name": "Distributor",
             "username": username,
             "pass": userpass,
             "name": distname,
            "address": address,
            "phone_number": phoneno
        }
       
    xhttp.onreadystatechange = function(){
        
        if(this.readyState == 4 && this.status == 200){
            let response = this.responseText;
           // alert("response: "+response);
            if(response.length!=0)
            {
                document.getElementById("errmsg").innerHTML = "Distributor Created";
            }
            else{
                document.getElementById("errmsg").innerHTML = " ";
            }
        }
    }
   // alert("Front end:"+JSON.stringify(userdetails));
    
   xhttp.open("POST", "http://localhost:8080/users/adduser", true);

   xhttp.setRequestHeader('Content-Type', 'application/json');
    
   xhttp.send(JSON.stringify(userdetails));
}
}

function logout(){
    sessionStorage.myObject = " ";
    window.id = " ";
    window.rolename = " ";
    window.distributor_id = " ";
}
function adduser(){
    //alert("add user");
    let srcstr = 'dist_details.html?id='+window.id;
    document.getElementById("dispuserpage").innerHTML = "<iframe style='position:relative;width:70%;height:85vh;' src="+srcstr+"></iframe>";
}
function placeorder(){
    //alert("Place Order");
    let srcstr = 'supplierOrderCreate.html?id='+window.id;
    document.getElementById("dispuserpage").innerHTML = "<iframe style='position:relative;width:70%;height:85vh;' src="+srcstr+"></iframe>";
}
function manageorder(){
    //alert("Manage Order");
    let srcstr = 'supplierOrderViewUpdate.html?id='+window.id;
    document.getElementById("dispuserpage").innerHTML = "<iframe style='position:relative;width:70%;height:85vh;' src="+srcstr+"></iframe>";

}
function ware_details(){
   // alert("Warehouse details");
    let srcstr = 'warehouse.html?id='+window.id;
    document.getElementById("dispuserpage").innerHTML = "<iframe style='position:relative;width:70%;height:85vh;' src="+srcstr+ "></iframe>";
}
function show_orderform(){
    //alert("inside show_orderform");
    let srcstr = 'distributor_order.html?id='+window.distributor_id;
    document.getElementById("dispuserpage").innerHTML = "<iframe style='position:relative;width:70%;height:85vh;' src="+srcstr+"></iframe>";
  }
function show_orderdetails(){
  //  alert("inside show_orderdetails");
    let srcstr = 'distributor.html?id='+window.distributor_id;
    document.getElementById("dispuserpage").innerHTML = "<iframe style='position:relative;width:70%;height:85vh;' src="+srcstr+"></iframe>";

}