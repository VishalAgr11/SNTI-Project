<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form by Colorlib</title>

<!-- Font Icon -->
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>

<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">

<div class="main">
    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>

                    <form method="post" action="register" class="register-form" id="register-form" onsubmit="return validateForm()">
                        <div class="form-group">
                            <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="name" id="name" placeholder="Your Name" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="email"><i class="zmdi zmdi-email"></i></label>
                            <input type="email" name="email" id="email" placeholder="Your Email" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="pass" id="pass" placeholder="Password" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                            <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="dob"><i class="zmdi zmdi-calendar"></i> &nbsp;&nbsp;&nbsp;Date of Birth</label><br><br>
                            <input type="date" name="dob" id="dob" placeholder="Date of Birth" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="doj"><i class="zmdi zmdi-calendar"></i> &nbsp;&nbsp;&nbsp;Date of Joining</label><br><br>
                            <input type="date" name="doj" id="doj" placeholder="Date of Joining" required="required"/>
                        </div>
                        <div class="form-group">
                            <label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
                            <input type="text" name="contact" id="contact" placeholder="Contact no" />
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" required="required"/>
                            <label for="agree-term" class="label-agree-term" >
                                <span><span></span></span>I agree to all statements in <a href="#" class="term-service">Terms of service</a>
                            </label>
                        </div>
                        <div class="g-recaptcha" data-sitekey="6Ldn6L0oAAAAAKcv0EE6j5XP9DKgAGlydvjT_f6X"></div>
                        <input type="hidden" id="g-recaptcha-response" name="g-recaptcha-response">
             
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure>
                        <img src="images/signup-image.jpg" alt="sign up image">
                    </figure>
                    <a href="login.jsp" class="signup-image-link">I am already a member</a>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<script>
function validateForm() {
    var recaptchaResponse = document.getElementById("g-recaptcha-response").value;
    if (recaptchaResponse === "") {
        alert("Please complete the reCAPTCHA verification.");
        return false;
    }
    return true; // Continue with form submission
}
</script>

<script type="text/javascript">

var status=document.getElementById("status").value;

if(status=="success"){
	swal("Congrats", "Account Created Successfully", "success");
}
if(status=="invalidName"){
	swal("Soory", "Please enter Name", "error");
}
if(status=="invalidEmail"){
	swal("Sorry", "Please enter an email", "error");
}
if(status=="invalidMobileLength"){
	swal("Sorry", "Please enter a valid mobile number", "error");
}
if(status=="invalidPWD"){
	swal("Sorry", "Enter a Password", "error");
}
if(status=="invalidDOB"){
	swal("Sorry", "Enter a DOB", "errror");
}
if(status=="invalidDOJ"){
	swal("Sorry", "Enter a DOJ", "error");
}
if(status=="invalidConfirmPWD"){
	swal("Sorry", "The 2 passwords do not match", "error");
}

</script>

</body>
<!-- This template was made by Colorlib (https://colorlib.com) -->
</html>
