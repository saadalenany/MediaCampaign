  <#if socialPlatforms??>
      <#list socialPlatforms as socialPlatform>
        <div class="col-md-12">
          <div class="card">
            <div class="card-header card-header-primary">
              <h4 class="card-title ">${socialPlatform.getName()}</h4>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-hover">
                  <thead class=" text-primary">
                    <tr>
                        <th>Campaign Name</th>
                        <th>Scrap Limitation</th>
                        <th>Active</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                  </thead>
                  <tbody>
                  <#if socialPlatform.getCampaigns()??>
                      <#list socialPlatform.getCampaigns() as campaign>
                        <tr>
                          <td>
                              <#if campaign.getName()??>
                              ${campaign.getName()}
                              </#if>
                          </td>
                          <td>
                              <#if campaign.getName()??>
                              ${campaign.getScrapLimitation()}
                              </#if>
                          </td>
                          <#if campaign.getActive() == true>
                          <td>
                              <a class="nav-link" href="#pablo">
                                <i class="material-icons">pause</i>
                              </a>
                          </td>
                          <#else>
                          <td>
                              <a class="nav-link" href="#pablo">
                                <i class="material-icons">play_arrow</i>
                              </a>
                          </td>
                          </#if>
                          <td>
                              <a class="nav-link" href="#pablo">
                                <i class="material-icons">edit</i>
                              </a>
                          </td>
                          <td>
                              <a class="nav-link" href="#pablo">
                                <i class="material-icons">delete</i>
                              </a>
                          </td>
                        </tr>
                      </#list>
                  <#else>
                        No entries in table
                  </#if>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </#list>
  <#else>
    <div class="alert alert-info col-md-12">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <i class="material-icons">close</i>
      </button>
      <span>
        <b> Info - </b> No Social Platform exists, Create A social platform now. </span>
    </div>
  </#if>