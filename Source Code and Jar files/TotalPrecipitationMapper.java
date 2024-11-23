import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TotalPrecipitationMapper extends Mapper<Object, Text, Text, FloatWritable> {

    private Text stationKey = new Text();
    private FloatWritable precipitationValue = new FloatWritable();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Skip header line
        if (value.toString().contains("index")) {
            return;
        }

        String[] fields = value.toString().split(",");
        
        try {
            // Assuming the precipitation is in the fourth column
            String station = fields[23];  // Column: station code
            float precipitation = Float.parseFloat(fields[4]); // Column: Precipitation (mm)
            
            stationKey.set(station);
            precipitationValue.set(precipitation);
            
            context.write(stationKey, precipitationValue);
        } catch (Exception e) {
            // Handle parsing errors, if needed
            System.err.println("Error parsing line: " + value.toString());

        }
    }
}

