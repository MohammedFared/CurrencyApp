package com.currency.android.Models;

/**
 * Currency Created by Mohammed Fareed on 11/2/2016.
 */

public class CurrencyModel {

    /**
     * count : 2
     * created : 2016-11-01T22:15:40Z
     * lang : en-US
     * diagnostics : {"url":[{"execution-start-time":"0","execution-stop-time":"87","execution-time":"87","content":"http://www.datatables.org/yahoo/finance/yahoo.finance.xchange.xml"},{"execution-start-time":"90","execution-stop-time":"91","execution-time":"1","content":"http://download.finance.yahoo.com/d/quotes.csv?s=EGPEGP=X&f=snl1d1t1ab"},{"execution-start-time":"90","execution-stop-time":"96","execution-time":"6","content":"http://download.finance.yahoo.com/d/quotes.csv?s=USDCHF=X&f=snl1d1t1ab"}],"publiclyCallable":"true","cache":[{"execution-start-time":"89","execution-stop-time":"89","execution-time":"0","method":"GET","type":"MEMCACHED","content":"b692fac57940968736739b2368a13435"},{"execution-start-time":"89","execution-stop-time":"89","execution-time":"0","method":"GET","type":"MEMCACHED","content":"d69f38521719d58f343c9657edf0ad59"}],"query":[{"execution-start-time":"89","execution-stop-time":"92","execution-time":"3","content":"select * from csv where url='http://download.finance.yahoo.com/d/quotes.csv?s=EGPEGP=X&f=snl1d1t1ab' and columns='Symbol,Name,Rate,Date,Time,Ask,Bid'"},{"execution-start-time":"89","execution-stop-time":"96","execution-time":"7","content":"select * from csv where url='http://download.finance.yahoo.com/d/quotes.csv?s=USDCHF=X&f=snl1d1t1ab' and columns='Symbol,Name,Rate,Date,Time,Ask,Bid'"}],"javascript":[{"execution-start-time":"88","execution-stop-time":"92","execution-time":"4","instructions-used":"30662","table-name":"yahoo.finance.xchange"},{"execution-start-time":"88","execution-stop-time":"96","execution-time":"8","instructions-used":"37324","table-name":"yahoo.finance.xchange"}],"user-time":"98","service-time":"94","build-version":"2.0.76"}
     * results : {"rate":[{"id":"EGPEGP","Name":"EGP/EGP","Rate":"1.0000","Date":"11/1/2016","Time":"6:09pm","Ask":"1.0023","Bid":"1.0000"},{"id":"USDCHF","Name":"USD/CHF","Rate":"0.9739","Date":"11/1/2016","Time":"6:58pm","Ask":"0.9744","Bid":"0.9739"}]}
     */

    private QueryBean query;
    /**
     * query : {"count":2,"created":"2016-11-24T15:14:11Z","lang":"en-US","diagnostics":{"url":[{"execution-start-time":"1","execution-stop-time":"2","execution-time":"1","content":"http://www.datatables.org/yahoo/finance/yahoo.finance.xchange.xml"},{"execution-start-time":"5","execution-stop-time":"6","execution-time":"1","content":"http://download.finance.yahoo.com/d/quotes.csv?s=USDCHF=X&f=snl1d1t1ab"},{"execution-start-time":"5","execution-stop-time":"7","execution-time":"2","content":"http://download.finance.yahoo.com/d/quotes.csv?s=AFNEGP=X&f=snl1d1t1ab"}],"publiclyCallable":"true","cache":[{"execution-start-time":"4","execution-stop-time":"4","execution-time":"0","method":"GET","type":"MEMCACHED","content":"d69f38521719d58f343c9657edf0ad59"},{"execution-start-time":"4","execution-stop-time":"4","execution-time":"0","method":"GET","type":"MEMCACHED","content":"5e0e99dc850d86b27010b1221b8f777d"}],"query":[{"execution-start-time":"4","execution-stop-time":"6","execution-time":"2","content":"select * from csv where url='http://download.finance.yahoo.com/d/quotes.csv?s=USDCHF=X&f=snl1d1t1ab' and columns='Symbol,Name,Rate,Date,Time,Ask,Bid'"},{"execution-start-time":"4","execution-stop-time":"7","execution-time":"3","content":"select * from csv where url='http://download.finance.yahoo.com/d/quotes.csv?s=AFNEGP=X&f=snl1d1t1ab' and columns='Symbol,Name,Rate,Date,Time,Ask,Bid'"}],"javascript":[{"execution-start-time":"2","execution-stop-time":"6","execution-time":"3","instructions-used":"30666","table-name":"yahoo.finance.xchange"},{"execution-start-time":"2","execution-stop-time":"7","execution-time":"4","instructions-used":"37330","table-name":"yahoo.finance.xchange"}],"user-time":"8","service-time":"4","build-version":"2.0.79"},"results":{"rate":[{"id":"AFNEGP","Name":"AFN/EGP","Rate":"0.2605","Date":"11/24/2016","Time":"5:42am","Ask":"0.2755","Bid":"0.2605"},{"id":"USDCHF","Name":"USD/CHF","Rate":"1.0159","Date":"11/24/2016","Time":"10:48am","Ask":"1.0161","Bid":"1.0159"}]}}
     */

    public QueryBean getQuery() {
        return query;
    }

    public static class QueryBean{

        private ResultsBean results;

        public ResultsBean getResults() {
            return results;
        }

        public static class ResultsBean{
            private RatesBean rate;
//            private ArrayList<RatesBean> rate = new ArrayList<>();
//            public ArrayList<RatesBean> getRates() {
//                return rate;
//            }

            public RatesBean getRates() {
                return rate;
            }

            public static class RatesBean{
                private String id;
                private String Rate;

                public String getRate() {
                    return Rate;
                }

                public String getId() {
                    return id;
                }

            }
        }
    }
}
