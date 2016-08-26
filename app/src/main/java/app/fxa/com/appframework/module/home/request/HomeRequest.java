package app.fxa.com.appframework.module.home.request;

import java.util.List;
import java.util.Map;

import app.fxa.com.appframework.common.restful.RestClient;
import app.fxa.com.appframework.common.restful.RestResponse;
import app.fxa.com.appframework.common.restful.RestResponseListener;
import app.fxa.com.appframework.common.wifiupload.BaseRequest;
import app.fxa.com.appframework.module.home.model.Book;
import app.fxa.com.appframework.module.home.request.rest.HomeService;
import retrofit2.Call;

/**
 * Created by fengxiang on 2016/8/23.
 */
public class HomeRequest extends BaseRequest {

    public void test(String url, RestResponseListener<RestResponse> listener) {
        HomeService homeService = RestClient.createRest(HomeService.class);
        Call<RestResponse> call = homeService.test(url);
        call.enqueue(listener);
    }

    @Override
    public void execute(Map<String, Object> map, RestResponseListener<RestResponse> listener) {
        test(String.valueOf(map.get("url")), listener);
    }

    public void books(RestResponseListener<RestResponse<List<Book>>> listener) {
        HomeService homeService = RestClient.createRest(HomeService.class);
        Call<RestResponse<List<Book>>> call = homeService.books(1);
        call.enqueue(listener);
    }

}
