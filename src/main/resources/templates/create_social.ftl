<#include "header.ftl">
  <div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Create Social Platform</h4>
                </div>
                <div class="card-body">
                  <form method="POST" action="/platform/save">
                    <br/>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Platform Name</label>
                          <input type="text" class="form-control" name="name" required>
                        </div>
                      </div>
                    </div>
                    <br/>
                    <h4 class="card-title">Add your Credentials</h4>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Access Token</label>
                          <input type="text" class="form-control" name="access_token" required>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">App ID</label>
                          <input type="text" class="form-control" name="app_id" required>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">App Secret</label>
                          <input type="text" class="form-control" name="app_secret" required>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary pull-right">Save Platform</button>
                        </div>
                      </div>
                    </div>
                    <div class="clearfix"></div>
                  </form>
                </div>
              </div>
            </div>
        </div>
    </div>
  </div>
<#include "footer.ftl">
