  <#if socialPlatforms??>
    <div class="col-md-12">
      <div class="card">
        <div class="card-header card-header-primary">
          <h4 class="card-title ">Social Platforms</h4>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-hover">
              <thead class="text-primary">
                <th>Platform Name</th>
                <th>Access Token</th>
                <th>App ID</th>
                <th>App Secret</th>
                <th>Actions<th>
              </thead>
              <tbody>
                <#list socialPlatforms as socialPlatform>
                  <tr>
                    <td>${socialPlatform.getName()}</td>
                    <td>${socialPlatform.getAccessToken()}</td>
                    <td>${socialPlatform.getAppId()}</td>
                    <td>${socialPlatform.getAppSecret()}</td>
                    <td>
                      <a class="nav-link" href="/platform/edit/${socialPlatform.getId()}">
                        <i class="material-icons">edit</i>
                      </a>
                      <a class="nav-link" href="/platform/delete/${socialPlatform.getId()}">
                         <i class="material-icons">delete</i>
                      </a>
                    </td>
                  </tr>
                </#list>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  <#else>
    <div class="alert alert-info col-md-12">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <i class="material-icons">close</i>
      </button>
      <span>
        <b> Info - </b> No Social Platform exists, Create A social platform now. </span>
    </div>
  </#if>
