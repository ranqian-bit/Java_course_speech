import java.util.ArrayList;
import java.util.Scanner;

/**
 * 餐厅结账单程序
 */
public class YourNameBillAmount {
    // 内部类，用于存储菜品的完整信息
    static class Caipin {
        String name;        // 菜品名称
        int shuliang;       // 数量
        double danjia;      // 单价
        double jine;        // 金额
        
        public Caipin(String name, int shuliang, double danjia, double jine) {
            this.name = name;
            this.shuliang = shuliang;
            this.danjia = danjia;
            this.jine = jine;
        }
    }
    
    // 主方法
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> shujiaList = new ArrayList<>();
        ArrayList<Caipin> caipinList = new ArrayList<>(); // 存储所有菜品的详细信息
        
        // 获取顾客姓名
        System.out.print("请输入顾客姓名：");
        String gukeXingming = sc.nextLine();
            
        System.out.printf("%30s\n", gukeXingming + "餐厅结账单");
            System.out.println("----------------------------------------");
            System.out.printf("%-10s %-10s %-10s %-8s\n", "菜品名称", "数量（份）", "单价（元）", "金额（元）");
            System.out.println("----------------------------------------");    
        
        // 用户输入菜品信息
        while (true) {

            System.out.print("请输入菜品名称（输入'结束'退出）：");
            String caipinMingcheng = sc.nextLine().trim(); // 去除前后空格
            
            // 立即检查是否为退出命令
            if ("结束".equals(caipinMingcheng)) {

                break;
            }
            
            try {
                System.out.print("请输入数量（份）：");
                // 确保有下一个整数输入
                while (!sc.hasNextInt()) {
                    System.out.println("请输入有效的数字！");
                    sc.next(); // 跳过无效输入
                }
                int shuliang = sc.nextInt();
                
                System.out.print("请输入单价（元）：");
                // 确保有下一个数字输入
                while (!sc.hasNextDouble()) {
                    System.out.println("请输入有效的金额！");
                    sc.next(); // 跳过无效输入
                }
                double danjia = sc.nextDouble();
                sc.nextLine(); // 读取换行符
                
                // 计算并存储菜品金额
                double caipinJine = jisuanCaipinJine(caipinMingcheng, shuliang, danjia);
                shujiaList.add(caipinJine);
                // 存储菜品完整信息
                caipinList.add(new Caipin(caipinMingcheng, shuliang, danjia, caipinJine));
            } catch (Exception e) {
                System.out.println("输入格式错误，请重新输入！");
                // 确保Scanner未关闭且有下一行输入
                if (sc.hasNextLine()) {
                    sc.nextLine(); // 清除错误输入
                }
            }
        }

        
        



        // 总菜单 - 显示所有菜品记录
        System.out.printf("%30s\n", gukeXingming + "餐厅结账单");
        System.out.println("----------------------------------------");
        // 显示菜品表头
        System.out.printf("%-10s %-10s %-10s %-8s\n", "菜品名称", "数量（份）", "单价（元）", "金额（元）");
        System.out.println("----------------------------------------");
        // 显示所有菜品记录
        for (Caipin cp : caipinList) {
            System.out.printf("%-10s %-10d %-10.1f %-8.1f\n", cp.name, cp.shuliang, cp.danjia, cp.jine);
        }
        System.out.println("----------------------------------------");
        
        // 计算消费总计
        double xiaofeiZongji = jisuanZongji(shujiaList);
        System.out.println("消费总计（元）：" + xiaofeiZongji);
        
        // 计算优惠金额
        double youhuiJine = jisuanYouhui(xiaofeiZongji);
        System.out.println("优惠金额（元）：" + youhuiJine);
        
        // 计算实收金额（包含抹零）
        int shishouJine = jisuanShishou(xiaofeiZongji, youhuiJine);
        System.out.println("实收金额（元）：" + shishouJine);
        
        sc.close();
    }
    
    // 计算单个菜品金额
    public static double jisuanCaipinJine(String caipinMingcheng, int shuliang, double danjia) {
        double caipinJine = shuliang * danjia;
        // 输入时不需要显示，最后统一显示所有菜品
        return caipinJine;
    }
    
    // 计算消费总计
    public static double jisuanZongji(ArrayList<Double> shujiaList) {
        double zongji = 0;
        for (double caipinJine : shujiaList) {
            zongji += caipinJine;
        }
        return zongji;
    }
    
    // 计算优惠金额（消费超过100元，8.8折）
    public static double jisuanYouhui(double zongji) {
        if (zongji > 100) {
            return zongji * (1 - 0.88);
        }
        return 0;
    }
    
    // 计算实收金额（抹零）
    public static int jisuanShishou(double zongji, double youhui) {
        double zhehouJine = zongji - youhui;
        return (int) zhehouJine; // 强制类型转换实现抹零
    }
}