<html>
    <head>
        <title>Login to Enter!</title>
        <script src="js/materialize.js"></script>
        <link rel="stylesheet" href="css/materialize.css">
        <link rel="stylesheet" href="css/w3.css">
        <!-- Fonts and icons -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- CSS Files -->
        <link href="/css/material-dashboard.css?v=2.1.1" rel="stylesheet" />
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link href="/demo/demo.css" rel="stylesheet" />
        <style>
            html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
            body {
                font-family: Arial, Helvetica, sans-serif;
                display: -webkit-flex; /* Safari */
                -webkit-flex-wrap: wrap; /* Safari 6.1+ */
                display: flex;
                flex-wrap: wrap;
                height:auto;
                margin:0;
                color:#fff;
                background: linear-gradient(90deg, rgba(57,177,99,1) 11%, rgba(13,119,156,1) 48%, rgba(0,212,255,1) 100%);
            }
            *,:after,:before{box-sizing:border-box}
            .clearfix:after,.clearfix:before{content:'';display:table}
            .clearfix:after{clear:both;display:block}
            a{color:inherit;text-decoration:none}

            input:focus,
            select:focus,
            textarea:focus,
            button:focus {
                outline: none;
            }
            .login-wrap{
                width:100%;
                margin:auto;
                position:relative;
                box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
            }
            .login-html{
                width:30%;
                margin-left: 35%;
                margin-right: 35%;
                position:absolute;
                padding:90px 70px 50px 70px;
            }
            .login-html .sign-in-htm,
            .login-html .sign-up-htm{
                top:0;
                left:0;
                right:0;
                bottom:0;
                position:absolute;
                transform:rotateY(180deg);
                backface-visibility:hidden;
                transition:all .4s linear;
            }
            .login-html .sign-in,
            .login-html .sign-up,
            .login-form .group .check{
                cursor: hand;
                display:none;
            }
            .login-html .tab,
            .login-form .group .label,
            .login-form .group .button{
                text-transform:uppercase;
            }
            .login-html .tab{
                color: #dddddd;
                cursor: hand;
                font-size:22px;
                margin-right:15px;
                padding-bottom:5px;
                margin:0 15px 10px 0;
                display:inline-block;
                border-bottom:2px solid transparent;
            }
            .login-html .sign-in:checked + .tab,
            .login-html .sign-up:checked + .tab{
                color:#fff;
                border-color:#fff;
            }
            .login-form{
                outline: none;
                margin-top:10px;
                min-height:345px;
                position:relative;
                perspective:1000px;
                transform-style:preserve-3d;
            }
            .login-form .group{
                margin-bottom:15px;
            }
            .login-form .group .label,
            .login-form .group .input,
            .login-form .group .button{
                outline: none;
                width:100%;
                color:#fff;
                display:block;
            }
            .login-form .group .input::focus{
                outline: none;
            }
            .login-form .group .input,
            .login-form .group .button{
                border:none;
                padding:15px 20px;
                border-radius:25px;
                background:rgba(255,255,255,.1);
            }
            .login-form .group input[data-type="password"]{
                text-security:circle;
                -webkit-text-security:circle;
            }
            .login-form .group .label{
                color:#fff;
                font-size:12px;
            }
            .login-form .group .button{
                cursor: hand;
                background:#1161ee;
            }
            .login-form .group label .icon{
                width:15px;
                height:15px;
                border-radius:2px;
                position:relative;
                display:inline-block;
                background:rgba(255,255,255,.1);
            }
            .login-form .group label .icon:before,
            .login-form .group label .icon:after{
                content:'';
                width:10px;
                height:2px;
                background:#fff;
                position:absolute;
                transition:all .2s ease-in-out 0s;
            }
            .login-form .group label .icon:before{
                left:3px;
                width:5px;
                bottom:6px;
                transform:scale(0) rotate(0);
            }
            .login-form .group label .icon:after{
                top:6px;
                right:0;
                transform:scale(0) rotate(0);
            }
            .login-form .group .check:checked + label{
                color:#fff;
            }
            .login-form .group .check:checked + label .icon{
                background:#1161ee;
            }
            .login-form .group .check:checked + label .icon:before{
                transform:scale(1) rotate(45deg);
            }
            .login-form .group .check:checked + label .icon:after{
                transform:scale(1) rotate(-45deg);
            }
            .login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
                transform:rotate(0);
            }
            .login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
                transform:rotate(0);
            }

            .hr{
                height:2px;
                margin:60px 0 50px 0;
                background:rgba(255,255,255,.2);
            }
            .foot-lnk{
                cursor: hand;
                text-align:center;
            }
            select option {
                margin: 40px;
                background: rgba(0, 0, 0, 0.3);
                color: #fff;
                text-shadow: 0 1px 0 rgba(0, 0, 0, 0.4);
            }

        </style>
    </head>
    <body id="particles-js">
        <br/>
        <br/>
        <div class="login-wrap">
            <div class="login-html">
                <#if errorMessage??>
                  <br/>
                  <div class="alert alert-danger">
                    <span>${errorMessage}</span>
                  </div>
                  <br/>
                </#if>
                <div class="login-form">
                    <form action="/checkLogin" method="post" autocomplete="off">
                        <br/>
                        <div class="input-field col s6">
                            <label for="user" class="label">Username</label>
                            <input autocomplete="fakeusername" style="color:#fff" id="user" name="name" type="text" class="input" required>
                        </div>
                        <br/>
                        <div class="input-field col s6">
                            <label for="pass" class="label">Password</label>
                            <input style="color:#fff" id="pass" name="pass" type="password" class="input" data-type="password" required>
                        </div>
                        <br/>
                        <div class="group">
                            <input type="submit" class="button" value="Sign In">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/particles.js"></script>
        <script src="js/app.js"></script>
    </body>
</html>
