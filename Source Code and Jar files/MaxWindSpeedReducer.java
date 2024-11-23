import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MaxWindSpeedReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

    private FloatWritable result = new FloatWritable();

    @Override
    public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        float maxSpeed = 0.0f;

        for (FloatWritable val : values) {
            maxSpeed = Math.max(maxSpeed, val.get());
        }

        result.set(maxSpeed);
        context.write(key, result);
    }
}


