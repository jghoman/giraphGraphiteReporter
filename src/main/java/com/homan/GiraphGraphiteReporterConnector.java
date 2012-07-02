package com.homan;

import com.yammer.metrics.core.MetricsRegistry;
import com.yammer.metrics.reporting.GraphiteReporter;
import org.apache.giraph.metrics.RegisterMetricsReporter;
import org.apache.hadoop.conf.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: jhoman
 * Date: 7/2/12
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GiraphGraphiteReporterConnector implements RegisterMetricsReporter {
  @Override
  public void registerReporter(MetricsRegistry metricsRegistry, Configuration conf) {
    String serverAddr = conf.get("giraph.graphite.server");
    String serverPort = conf.get("giraph.graphite.server.port");
    if(serverAddr == null || serverPort == null) {
      System.err.println("GiraphGraphiteReporterConnector not in conf.  Not configuring");
    }
    GraphiteReporter.enable(metricsRegistry, 30, TimeUnit.SECONDS, serverAddr, Integer.valueOf(serverPort));
  }
}
