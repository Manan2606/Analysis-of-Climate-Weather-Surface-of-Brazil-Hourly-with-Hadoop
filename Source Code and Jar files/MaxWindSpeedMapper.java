import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxWindSpeedMapper extends Mapper<Object, Text, Text, FloatWritable> {

    private Text station = new Text();
    private FloatWritable windSpeed = new FloatWritable();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] columns = value.toString().split(",");  // Assuming CSV format

        try {
            // Extract station and wind speed
            station.set(columns[22]);  // Station name (adjust index if needed)
            windSpeed.set(Float.parseFloat(columns[19]));  // Maximum wind speed column
            context.write(station, windSpeed);
        } catch (Exception e) {
            // Handle malformed records
        }
    }
}


