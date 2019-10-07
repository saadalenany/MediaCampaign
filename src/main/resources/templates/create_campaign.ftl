<#include "header.ftl">
  <div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
              <div class="card">
                <div class="card-header card-header-primary">
                  <h4 class="card-title">Create Campaign</h4>
                </div>
                <div class="card-body">
                  <form>
                    <br/>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Campaign Name</label>
                          <input type="text" class="form-control">
                        </div>
                      </div>
                    </div>
                    <br/>
                    <h4 class="card-title">Add your Page Authentication</h4>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Page URL</label>
                          <input type="text" class="form-control">
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Username</label>
                          <input type="text" class="form-control">
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Password</label>
                          <input type="text" class="form-control">
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-3">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Scrap Limitation</label>
                          <input type="number" class="form-control">
                        </div>
                      </div>
                      <div class="col-md-3">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Number of Posts</label>
                          <input type="number" class="form-control">
                        </div>
                      </div>
                      <label class="bmd-label-floating">Per</label>
                      <div class="col-md-3">
                        <div class="form-group bmd-form-group">
                          <select class="form-control">
                            <option class="form-control" value="minute">Minute</option>
                            <option class="form-control" value="hour">Hour</option>
                            <option class="form-control" value="day">Day</option>
                            <option class="form-control" value="week">Week</option>
                            <option class="form-control" value="month">Month</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Posts Type</label>
                          <input class="form-control" type="checkbox" name="text" value="Text" checked> Texts
                          <input class="form-control" type="checkbox" name="image" value="Image"> Images
                          <input class="form-control" type="checkbox" name="video" value="Video"> Videos
                          <input class="form-control" type="checkbox" name="share" value="Share"> Shares
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group bmd-form-group">
                          <label class="bmd-label-floating">Block Keywords</label>
                          <input type="text" class="form-control">
                        </div>
                      </div>
                    </div>
                    <br/>
                    <div class="row">
                      <div class="col-md-4">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary pull-right">Publish And Start</button>
                        </div>
                      </div>
                      <div class="col-md-4">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary pull-right">Publish And Pause</button>
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
