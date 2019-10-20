<#include "header.ftl">
  <#if socialPlatforms??>
  <div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Update Campaign</h4>
                </div>
                <div class="card-body">
                  <form method="PUT" action="/campaign/update">
                    <input type="hidden" name="campaign_id" value="${campaign.getId()}"/>
                    <div class="row">
                      <div class="col-md-12">
                          <div class="form-group bmd-form-group">
                          <select class="form-control" name="social_platform" required>
                            <#list socialPlatforms as socialPlatform>
                                <#if campaign.getSocialPlatformId() == socialPlatform.getId()>
                                <option value="${socialPlatform.getId()}" selected>${socialPlatform.getName()}</option>
                                <#else>
                                <option value="${socialPlatform.getId()}">${socialPlatform.getName()}</option>
                                </#if>
                            </#list>
                          </select>
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group is-filled">
                          <label class="bmd-label-floating">Campaign Name</label>
                          <input type="text" class="form-control" name="name" value="${campaign.getName()}" required>
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-12">
                        <h4 class="card-title">Add your Page Authentication</h4>
                        <br/>
                        <div class="form-group bmd-form-group">
                            <ul class="nav nav-pills nav-pills-primary" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#target1" role="tablist" aria-expanded="true">
                                        Import from an existing page
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#target2" role="tablist" aria-expanded="false">
                                        Import from to a new page
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <br/>
                        <div class="form-group bmd-form-group">
                            <div class="tab-content tab-space">
                                <div class="tab-pane active" id="target1" aria-expanded="true">
                                  <#if targetPages?size != 0 >
                                  <select class="form-control" name="target_page_exists">
                                    <#list targetPages as page>
                                        <#if campaign.getTargetPageId() == page.getId()>
                                        <option class="form-control" value="${page.getId()}" selected>${page.getPageUrl()}</option>
                                        <#else>
                                        <option class="form-control" value="${page.getId()}">${page.getPageUrl()}</option>
                                        </#if>
                                    </#list>
                                  </select>
                                  <#else>
                                    <div class="alert alert-info col-md-12">
                                        <span>
                                          <b> Info - </b> No Target pages exist, Create A new Target page instead.
                                        </span>
                                    </div>
                                  </#if>
                                </div>
                                <div class="tab-pane" id="target2" aria-expanded="false">
                                  <label class="bmd-label-floating">Export to Page</label>
                                  <input type="text" class="form-control" name="target_page_new">
                                </div>
                            </div>
                        </div>
                        <br/><hr/><br/>
                        <div class="form-group bmd-form-group">
                            <ul class="nav nav-pills nav-pills-primary" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#source1" role="tablist" aria-expanded="true">
                                        Export to an existing page
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#source2" role="tablist" aria-expanded="false">
                                        Export to a new page
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <br/>
                        <div class="form-group bmd-form-group">
                            <div class="tab-content tab-space">
                                <div class="tab-pane active" id="source1" aria-expanded="true">
                                  <#if sourcePages?size != 0 >
                                      <select class="form-control" name="source_page_exists">
                                        <#list sourcePages as page>
                                            <#if campaign.getSourcePageId() == page.getId()>
                                            <option class="form-control" value="${page.getId()}" selected>${page.getPageUrl()}</option>
                                            <#else>
                                            <option class="form-control" value="${page.getId()}">${page.getPageUrl()}</option>
                                            </#if>
                                        </#list>
                                      </select>
                                  <#else>
                                      <div class="alert alert-info col-md-12">
                                        <span>
                                          <b> Info - </b> No Source pages exist, Create A new Source page instead.
                                        </span>
                                      </div>
                                  </#if>
                                </div>
                                <div class="tab-pane" id="source2" aria-expanded="false">
                                    <div class="form-group bmd-form-group">
                                      <label class="bmd-label-floating">Import from Page</label>
                                      <input type="text" class="form-control" name="source_page_new">
                                    </div>
                                    <br/>
                                    <div class="form-group bmd-form-group">
                                      <label class="bmd-label-floating">Username</label>
                                      <input type="text" class="form-control" name="username">
                                    </div>
                                    <br/>
                                    <div class="form-group bmd-form-group">
                                      <label class="bmd-label-floating">Password</label>
                                      <input type="text" class="form-control" name="password">
                                    </div>
                                </div>
                            </div>
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-3">
                        <div class="form-group bmd-form-group is-filled">
                          <label class="bmd-label-floating">Scrap Limitation</label>
                          <input type="number" class="form-control" name="scrap_limitation" value="${campaign.getScrapLimitation()}" required>
                        </div>
                      </div>
                      <div class="col-md-3">
                        <div class="form-group bmd-form-group is-filled">
                          <label class="bmd-label-floating">Number of Posts</label>
                          <input type="number" class="form-control" name="number_of_posts" value="${campaign.getNop()}" required>
                        </div>
                      </div>
                      <label class="bmd-label-floating">Per</label>
                      <div class="col-md-3">
                        <div class="form-group bmd-form-group">
                          <select class="form-control" name="per" required>
                            <option class="form-control" value="minute"
                            <#if campaign.getPer() == "minute">selected</#if>
                            >Minute</option>
                            <option class="form-control" value="hour"
                            <#if campaign.getPer() == "hour">selected</#if>
                            >Hour</option>
                            <option class="form-control" value="day"
                            <#if campaign.getPer() == "day">selected</#if>
                            >Day</option>
                            <option class="form-control" value="week"
                            <#if campaign.getPer() == "week">selected</#if>
                            >Week</option>
                            <option class="form-control" value="month"
                            <#if campaign.getPer() == "month">selected</#if>
                            >Month</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group bmd-form-group">
                          <input class="form-control" type="checkbox" name="source_with_ornot"
                          <#if campaign.getSourceWithOrnot()??>checked</#if>
                          > With Source Or Not
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Posts Type</label>
                          <input class="form-control" type="checkbox" name="post_type" value="Text"
                          <#if campaign.getPostType()?contains("Text")>checked</#if>
                          > Texts
                          <input class="form-control" type="checkbox" name="post_type" value="Image"
                          <#if campaign.getPostType()?contains("Image")>checked</#if>
                          > Images
                          <input class="form-control" type="checkbox" name="post_type" value="Video"
                          <#if campaign.getPostType()?contains("Video")>checked</#if>
                          > Videos
                          <input class="form-control" type="checkbox" name="post_type" value="Share"
                          <#if campaign.getPostType()?contains("Share")>checked</#if>
                          > Shares
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group bmd-form-group is-filled">
                          <label class="bmd-label-floating">Block Keywords</label>
                          <input type="text" class="form-control" name="block_keywords" <#if campaign.getBlockKeywords()??> value="${campaign.getBlockKeywords()}" </#if> required>
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group bmd-form-group">
                          <input class="form-control" type="checkbox" name="active"
                          <#if campaign.getActive()??>checked</#if>
                          > Start Immediately
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary pull-right">Update</button>
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
  <#else>
  <div class="alert alert-info col-md-12">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <i class="material-icons">close</i>
    </button>
    <span>
      <b> Info - </b> No Social Platform exists, Create A social platform now. </span>
  </div>
  </#if>
<#include "footer.ftl">
