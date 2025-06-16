package jp.jaxa.iss.kibo.rpc.defaultapk;

import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {
    @Override
    protected void runPlan1() {
        // 1. 開始任務
        api.startMission();

        // 2. 巡視第一個區域
        moveToArea(1);
        String item1 = identifyItem(1);

        // 3. 巡視第二個區域
        moveToArea(2);
        String item2 = identifyItem(2);

        // 4. 巡視第三個區域
        moveToArea(3);
        String item3 = identifyItem(3);

        // 5. 巡視第四個區域
        moveToArea(4);
        String item4 = identifyItem(4);

        // 6. 移動到宇航員位置報告完成
        moveToAstronaut();
        api.reportRoundingCompletion();

        // 7. 獲取目標物品資訊
        String targetItem = identifyTargetItem();
        api.notifyRecognitionItem();

        // 8. 找出目標物品位置
        int targetArea = findTargetItemArea(targetItem);

        // 9. 移動到目標物品位置
        moveToArea(targetArea);

        // 10. 拍攝目標物品並完成任務
        api.takeTargetItemSnapshot();
    }

    // 移動到指定區域
    private void moveToArea(int areaId) {
        Point point;
        Quaternion quaternion = new Quaternion(0, 0, -0.707, 0.707);

        switch (areaId) {
            case 1:
                point = new Point(10.9, -9.9, 5.2);
                break;
            case 2:
                point = new Point(10.9, -8.9, 4.8);
                break;
            case 3:
                point = new Point(10.9, -7.9, 4.8);
                break;
            case 4:
                point = new Point(10.9, -6.9, 5.2);
                break;
            default:
                return;
        }

        api.moveTo(point, quaternion, false);
    }

    // 識別區域中的物品
    private String identifyItem(int areaId) {
        Mat image = api.getMatNavCam();

        // 圖像處理邏輯（示例）
        String itemName = "unknown";
        int itemCount = 0;

        // 根據圖像識別結果設置物品名稱和數量
        // 這裡應該是您的圖像處理邏輯

        // 報告識別結果
        api.setAreaInfo(areaId, itemName, itemCount);

        return itemName;
    }

    // 移動到宇航員位置
    private void moveToAstronaut() {
        Point astronautPoint = new Point(11.2, -7.0, 5.0);
        Quaternion quaternion = new Quaternion(0, 0, 0.707, 0.707);
        api.moveTo(astronautPoint, quaternion, false);
    }

    // 識別目標物品
    private String identifyTargetItem() {
        Mat image = api.getMatNavCam();

        // 目標物品識別邏輯（示例）
        String targetItemName = "emerald";

        return targetItemName;
    }

    // 找出目標物品所在區域
    private int findTargetItemArea(String targetItem) {
        // 根據之前識別的結果找出目標物品所在區域
        // 這裡應該是您的邏輯
        return 2;  // 示例返回值
    }

    @Override
    protected void runPlan2(){
        // write your plan 2 here
    }

    @Override
    protected void runPlan3(){
        // write your plan 3 here
    }

}

