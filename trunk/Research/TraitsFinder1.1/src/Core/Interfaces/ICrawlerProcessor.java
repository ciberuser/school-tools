package Core.Interfaces;

import Core.ECrawlingType;

public interface ICrawlerProcessor
{
	boolean LoadProcessor(String processorFilePath);
	boolean IsDepthCrawling(String className);
	boolean IsDepthCrawling(ECrawlingType crawlType);
}
