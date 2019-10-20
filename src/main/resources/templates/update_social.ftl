<#include "header.ftl">
  <div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Update Social Platform</h4>
                </div>
                <div class="card-body">
                  <form method="PUT" action="/platform/update">
                    <input type="hidden" name="id"
                      <#if socialPlatform??>
                        value="${socialPlatform.getId()}"
                      </#if>
                    >
                    <br/>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Platform Name</label>
                          <input type="text" class="form-control" name="name"
                              <#if socialPlatform??>
                                value="${socialPlatform.getName()}"
                              </#if>
                          required>
                        </div>
                      </div>
                    </div>
                    <br/>
                    <h4 class="card-title">Add your Credentials</h4>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Access Token</label>
                          <input type="text" class="form-control" name="access_token"
                            <#if socialPlatform??>
                              value="${socialPlatform.getAccessToken()}"
                            </#if>
                          required>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">App ID</label>
                          <input type="text" class="form-control" name="app_id"
                            <#if socialPlatform??>
                              value="${socialPlatform.getAppId()}"
                            </#if>
                          required>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">App Secret</label>
                          <input type="text" class="form-control" name="app_secret"
                            <#if socialPlatform??>
                              value="${socialPlatform.getAppSecret()}"
                            </#if>
                          required>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary pull-right">Update Platform</button>
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
