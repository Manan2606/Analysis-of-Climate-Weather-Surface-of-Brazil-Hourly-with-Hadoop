import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AverageTemperatureMapper extends Mapper<Object, Text, Text, FloatWritable> {

    private Text station = new Text();
    private FloatWritable temperature = new FloatWritable();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] columns = value.toString().split(",");  // Assuming CSV format

        try {
            // Extract station name and temperature (Assuming correct column positions)
            station.set(columns[22]);  // Station name or code (adjust index if necessary)
            temperature.set(Float.parseFloat(columns[9]));  // Temperature column (adjust index if necessary)
            context.write(station, temperature);
        } catch (Exception e) {
            // Handle malformed records (skip invalid ones)
        }
    }
}

